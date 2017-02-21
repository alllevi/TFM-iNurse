package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.pacientes;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.AvisosDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores.AdapterMisAvisos;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.ConstantesComun;

public class MisAvisosActivity extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    private RecyclerView recyclerView;
    private List<AvisosDTO> listaAvisos;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_avisos);
        initComponentes();
        initTask();
    }

    private void initComponentes() {
        sharedpreferences = getSharedPreferences(ConstantesComun.SHARED_PREFS_FILE, ConstantesComun.CONTEXT_MODE_PRIVATE);
        recyclerView = (RecyclerView) findViewById(R.id.rv_mis_avisos);
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        final AdapterMisAvisos adapter = new AdapterMisAvisos(listaAvisos, this);
        recyclerView.setAdapter(adapter);
    }

    private void initTask() {
        if (checkInternet()) {
            MisAvisosTask misAvisosTask = new MisAvisosTask();
            misAvisosTask.execute((Void) null);
        }
    }

    @Override
    public void onBackPressed() {
        // Boton atras
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

    private class MisAvisosTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            String pacienteKey = sharedpreferences.getString(ConstantesComun.PACIENTE_KEY, "");
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
