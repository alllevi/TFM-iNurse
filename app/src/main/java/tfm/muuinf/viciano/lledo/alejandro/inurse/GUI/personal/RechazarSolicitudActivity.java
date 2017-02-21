package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;

public class RechazarSolicitudActivity extends AppCompatActivity {

    private Button btRechazar;
    private Button btCancelar;
    private TextView tvDescripcion;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechazar_solicitud);
        initComponentes();
        setListeners();
    }

    private void initComponentes() {
        btRechazar = (Button) findViewById(R.id.bt_rechazar_solicitud);
        btCancelar = (Button) findViewById(R.id.bt_cancelar_solicitud);
        tvDescripcion = (TextView) findViewById(R.id.tv_motivo_rechazar_solicitud);
    }

    private void setListeners() {
        btRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTask();
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initTask() {
        if (checkInternet()) {
            RechazarSolicitudTask solicitudesTask = new RechazarSolicitudTask();
            solicitudesTask.execute((Void) null);
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

    @Override
    public void onBackPressed() {
        finish();
    }

    private class RechazarSolicitudTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                //Realizar llamada correspondiente
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                //Ha ido bien?
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
