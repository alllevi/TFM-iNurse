package tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.pacientes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class RealizarSolicitudActivity extends AppCompatActivity {

    private Spinner cbPrioridades;
    private ListView lvRealizarSolicitud;
    private Button btRealizarSolicitud;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_solicitud);
        initComponentes();
        rellenarComboPrioridades();

    }

    private void initComponentes() {
        cbPrioridades = (Spinner) findViewById(R.id.cb_solicitud_prioridad);
        lvRealizarSolicitud = (ListView) findViewById(R.id.lv_realizar_solicitud);
        btRealizarSolicitud = (Button) findViewById(R.id.bt_enviar_solicitud);
    }

    private void rellenarComboPrioridades() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.prioridades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbPrioridades.setAdapter(adapter);
    }

}
