package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class ConfigurarAvisosActivity extends AppCompatActivity {

    //Faltan los selectores de fecha
    private Spinner cbPlanta;
    private Spinner cbHabitacion;
    private CheckBox chRepetir;
    private TextView tvHoras;
    private TextView tvDescripcion;
    private Button btProgramar;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_avisos);
        initComponentes();
        rellenarCombPlantas();
        rellenarComboHabitaciones();
    }

    private void initComponentes() {
        cbPlanta = (Spinner) findViewById(R.id.cb_avisos_planta);
        cbHabitacion = (Spinner) findViewById(R.id.cb_avisos_habitacion);
        chRepetir = (CheckBox) findViewById(R.id.ch_avisos_repetir);
        tvHoras = (TextView) findViewById(R.id.tv_avisos_horas);
        tvDescripcion = (TextView) findViewById(R.id.tv_avisos_descripcion);
        btProgramar = (Button) findViewById(R.id.bt_alarmas_programar);
    }

    private void rellenarCombPlantas() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plantas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbPlanta.setAdapter(adapter);
    }

    private void rellenarComboHabitaciones() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.habitacion, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbHabitacion.setAdapter(adapter);
    }
}
