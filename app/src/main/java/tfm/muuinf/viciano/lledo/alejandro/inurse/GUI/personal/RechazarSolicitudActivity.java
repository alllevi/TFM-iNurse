package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class RechazarSolicitudActivity extends InurseActivity {

    private Integer solicitudKey;
    private Button btRechazar;
    private Button btCancelar;
    private TextView tvDescripcion;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechazar_solicitud);
        solicitudKey = getIntent().getExtras().getInt("solicitudKey");
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
            String motivo = tvDescripcion.getText().toString();
            RechazarSolicitudTask solicitudesTask = new RechazarSolicitudTask(motivo);
            solicitudesTask.execute((Void) null);
        }
    }

    private void returnResult() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("accion", "borrar");
        returnIntent.putExtra("solicitudKey", solicitudKey);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private class RechazarSolicitudTask extends AsyncTask<Void, Void, Boolean> {

        private String motivo;

        public RechazarSolicitudTask(String motivo) {
            this.motivo = motivo;
        }

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                return dal.getSolicitudDAO().updateToRechazada(solicitudKey, motivo);
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                returnResult();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
