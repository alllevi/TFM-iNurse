package tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.pacientes;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DAL.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.MaestroPrioridadesDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.MaestroSolicitudDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.comun.ConstantesGUI;
import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class RealizarSolicitudActivity extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    private RealizarSolicitudTask realizarSolicitudTask = null;
    private InsertarSolicitudTask insertarSolicitudTask = null;
    private ListView lvRealizarSolicitud;
    private TextView tvPrioridadSeleccionada;
    private EditText etDescripcionSolicitud;
    private Button btRealizarSolicitud;
    private List<MaestroSolicitudDTO> listaMaestroSolicitudesDTO;
    private List<MaestroPrioridadesDTO> listaMaestroPrioridadesDTO;
    private Integer indexSelectedSolicitud;
    private String descripcionSolicitud;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_solicitud);
        initComponentes();
        initTask();
    }

    private void initComponentes() {
        sharedpreferences = getSharedPreferences(ConstantesGUI.SHARED_PREFS_FILE, ConstantesGUI.CONTEXT_MODE_PRIVATE);
        lvRealizarSolicitud = (ListView) findViewById(R.id.lv_realizar_solicitud);
        tvPrioridadSeleccionada = (TextView) findViewById(R.id.tv_prioridad_seleccionada);
        etDescripcionSolicitud = (EditText) findViewById(R.id.et_descripcion_solicitud);
        btRealizarSolicitud = (Button) findViewById(R.id.bt_enviar_solicitud);
        btRealizarSolicitud.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                descripcionSolicitud = etDescripcionSolicitud.getText().toString();
                insertarTask();
            }
        });
    }

    private void rellenarListaSolicitudes() {
        List<String> arrayList = new ArrayList<>();
        for (MaestroSolicitudDTO maestro : listaMaestroSolicitudesDTO) {
            arrayList.add(maestro.getDescripcion());
        }
        ArrayAdapter<String> arrayAdapterSolicitudes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        lvRealizarSolicitud.setAdapter(arrayAdapterSolicitudes);
        lvRealizarSolicitud.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                indexSelectedSolicitud = position;
                Integer prioridad = listaMaestroSolicitudesDTO.get(position).getPrioridad();
                String descripcionPrioridad;
                for (MaestroPrioridadesDTO maestroP : listaMaestroPrioridadesDTO) {
                    if (maestroP.getPrioriodad().equals(prioridad)) {
                        tvPrioridadSeleccionada.setText(maestroP.getDescripcion());
                    }
                }
            }
        });
    }

    private void initTask() {
        final boolean conexion = checkInternet();
        if (conexion) {
            realizarSolicitudTask = new RealizarSolicitudTask();
            realizarSolicitudTask.execute((Void) null);
        }
    }

    private void insertarTask() {
        final boolean conexion = checkInternet();
        if (conexion) {
            insertarSolicitudTask = new InsertarSolicitudTask();
            insertarSolicitudTask.execute((Void) null);
        }
    }

    private boolean checkInternet() {

        final ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Compruebe su conexión a internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * Task para recuperar los maestros de solicitudes y prioridades
     */
    public class RealizarSolicitudTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            final ServiciosDAL dal = new ServiciosDAL();
            try {
                listaMaestroSolicitudesDTO = dal.getMaestrosDAO().getMaestroSolicitudes();
                listaMaestroPrioridadesDTO = dal.getMaestrosDAO().getMaestroPrioridades();
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                rellenarListaSolicitudes();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Task para insertar la solicitud en la base de datos
     */
    public class InsertarSolicitudTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            final ServiciosDAL dal = new ServiciosDAL();
            String pacienteKey = sharedpreferences.getString(ConstantesGUI.PACIENTE_KEY, "");
            String maestroSolicitudKey = listaMaestroSolicitudesDTO.get(indexSelectedSolicitud).getMaestroSolicitudKey().toString();
            try {
                return dal.getSolicitudDAO().insertarSolicitud(pacienteKey, maestroSolicitudKey, descripcionSolicitud);
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(getApplicationContext(), " La solicitud se ha insertado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
