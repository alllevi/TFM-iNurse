package tfm.muuinf.viciano.lledo.alejandro.inurse.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class RechazarSolicitudActivity extends AppCompatActivity {

    Button btRechazar;
    Button btCancelar;
    TextView tvDescripcion;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechazar_solicitud);

        initComponentes();
    }

    private void initComponentes() {
        this.btRechazar = (Button) findViewById(R.id.bt_rechazar_solicitud);
        this.btCancelar = (Button) findViewById(R.id.bt_cancelar_solicitud);
        this.tvDescripcion = (TextView) findViewById(R.id.tv_motivo_rechazar_solicitud);
    }

    @Override
    public void onBackPressed() {
        // Boton atras
    }
}
