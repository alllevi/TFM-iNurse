package tfm.muuinf.viciano.lledo.alejandro.inurse.personal;

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
    Spinner cbPlanta;
    Spinner cbHabitacion;
    CheckBox chRepetir;
    TextView tvHoras;
    TextView tvDescripcion;
    Button btProgramar;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_avisos);
        initComponentes();
        rellenarCombPlantas();
        rellenarComboHabitaciones();
    }

    private void initComponentes() {
        this.cbPlanta = (Spinner) findViewById(R.id.cb_avisos_planta);
        this.cbHabitacion = (Spinner) findViewById(R.id.cb_avisos_habitacion);
        this.chRepetir = (CheckBox) findViewById(R.id.ch_avisos_repetir);
        this.tvHoras = (TextView) findViewById(R.id.tv_avisos_horas);
        this.tvDescripcion = (TextView) findViewById(R.id.tv_avisos_descripcion);
        this.btProgramar = (Button) findViewById(R.id.bt_alarmas_programar);
    }

    private void rellenarCombPlantas() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plantas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.cbPlanta.setAdapter(adapter);
    }

    private void rellenarComboHabitaciones() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.habitacion, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.cbHabitacion.setAdapter(adapter);
    }
}
