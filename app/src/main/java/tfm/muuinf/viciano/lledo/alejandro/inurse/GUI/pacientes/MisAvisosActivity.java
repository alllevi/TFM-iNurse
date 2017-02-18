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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DAL.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.AvisosDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.adaptadores.AdapterMisAvisos;
import tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.comun.ConstantesGUI;
import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class MisAvisosActivity extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    private MisAvisosTask misAvisosTask = null;
    private RecyclerView recyclerView;
    private TextView tvFechaIni;
    private TextView tvFechaFin;
    private TextView tvFrecuencia;
    private TextView tvDescripcion;
    private List<AvisosDTO> listaAvisos;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_avisos);
        initComponentes();
        initTask();
    }

    private void initComponentes() {
        sharedpreferences = getSharedPreferences(ConstantesGUI.SHARED_PREFS_FILE, ConstantesGUI.CONTEXT_MODE_PRIVATE);
        recyclerView = (RecyclerView) findViewById(R.id.rv_mis_avisos);
        tvFechaIni = (TextView) findViewById(R.id.tv_mis_avisos_fecha_ini);
        tvFechaFin = (TextView) findViewById(R.id.tv_mis_avisos_fecha_fin);
        tvFrecuencia = (TextView) findViewById(R.id.tv_mis_avisos_frecuencia);
        tvDescripcion = (TextView) findViewById(R.id.tv_mis_avisos_descripcion);
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        final AdapterMisAvisos adapter = new AdapterMisAvisos(listaAvisos, this);
        recyclerView.setAdapter(adapter);
    }

    private void initTask() {
        if (checkInternet()) {
            misAvisosTask = new MisAvisosTask();
            misAvisosTask.execute((Void) null);
        }
    }

    private boolean checkInternet() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Compruebe su conexión a internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public class MisAvisosTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            String pacienteKey = sharedpreferences.getString(ConstantesGUI.PACIENTE_KEY, "");
            try {
                listaAvisos = dal.getAvisosDAO().getAvisosPaciente(pacienteKey);
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
