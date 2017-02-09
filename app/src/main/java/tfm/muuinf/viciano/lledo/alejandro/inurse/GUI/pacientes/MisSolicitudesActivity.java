package tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.pacientes;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DAL.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.SolicitudDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.adaptadores.AdapterMisSolicitudes;
import tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.comun.ConstantesGUI;
import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class MisSolicitudesActivity extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    private MisSolicitudesTask solicitudesTask = null;
    private Spinner comboEstados;
    private RecyclerView recyclerView;
    private List<SolicitudDTO> listaSolicitudesDTO;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_solicitudes);
        initComponents();
        rellenarComboEstados();
    }

    private void initComponents() {
        sharedpreferences = getSharedPreferences(ConstantesGUI.SHARED_PREFS_FILE, ConstantesGUI.CONTEXT_MODE_PRIVATE);
        comboEstados = (Spinner) findViewById(R.id.cb_filtro_mis_solicitudes);
        recyclerView = (RecyclerView) findViewById(R.id.rv_mis_solicitudes);
        obtenerSolicitudes();
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        final AdapterMisSolicitudes adapter = new AdapterMisSolicitudes(listaSolicitudesDTO);
        recyclerView.setAdapter(adapter);
    }

    private void rellenarComboEstados() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboEstados.setAdapter(adapter);
    }

    private void obtenerSolicitudes() {
        final boolean conexion = checkInternet();
        if (conexion) {
            solicitudesTask = new MisSolicitudesTask();
            solicitudesTask.execute((Void) null);
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

    public class MisSolicitudesTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            final ServiciosDAL dal = new ServiciosDAL();
            final String pacienteKey = sharedpreferences.getString(ConstantesGUI.PACIENTE_KEY, "");
            try {
                listaSolicitudesDTO = dal.getSolicitudDAO().getSolicitudesByPacienteKey(Integer.parseInt(pacienteKey));
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                rellenarRecyclerView();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
