package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.AvisoConfiguracionDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.DatePickerFragment;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.TimePickerFragment;

public class ConfigurarAvisosActivity extends InurseActivity {

    private AvisoConfiguracionDTO avisosConfiguracion;

    private Spinner cbPlantas;
    private Spinner cbHabitacion;
    private TextView tvPaciente;
    private CheckBox chRepetir;
    private TextView tvHoras;
    private TextView tvDescripcion;
    private Button btProgramar;
    private TextView tvDatePicker;
    private TextView tvTimePicker;
    private ImageView ivCalendar;
    private ImageView ivClock;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_avisos);
        initComponentes();
        initTask();
        setListeners();
    }

    private void initComponentes() {
        cbPlantas = (Spinner) findViewById(R.id.cb_avisos_planta);
        cbHabitacion = (Spinner) findViewById(R.id.cb_avisos_habitacion);
        tvPaciente = (TextView) findViewById(R.id.tv_avisos_paciente);
        chRepetir = (CheckBox) findViewById(R.id.ch_avisos_repetir);
        tvHoras = (TextView) findViewById(R.id.tv_avisos_horas);
        tvDescripcion = (TextView) findViewById(R.id.tv_avisos_descripcion);
        btProgramar = (Button) findViewById(R.id.bt_avisos_programar);
        tvDatePicker = (TextView) findViewById(R.id.tv_avisos_date_picker);
        tvTimePicker = (TextView) findViewById(R.id.tv_avisos_time_picker);
        ivCalendar = (ImageView) findViewById(R.id.iv_avisos_calendar);
        ivClock = (ImageView) findViewById(R.id.iv_avisos_clock);
    }

    private void rellenarCombPlantas() {
        List<String> listaPlantas = new ArrayList<>();
        listaPlantas.addAll(avisosConfiguracion.getMapHabitaciones().keySet());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPlantas);
        cbPlantas.setAdapter(arrayAdapter);

        cbPlantas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object selectedItem = cbPlantas.getSelectedItem();
                rellenarComboHabitaciones(avisosConfiguracion.getMapHabitaciones().get(selectedItem.toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void rellenarComboHabitaciones(List<String> listaHabitaciones) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaHabitaciones);
        cbHabitacion.setAdapter(arrayAdapter);

        cbHabitacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = cbPlantas.getSelectedItem().toString() + cbHabitacion.getSelectedItem().toString();
                tvPaciente.setText(avisosConfiguracion.getMapPacientes().get(key));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initTask() {
        if (checkInternet()) {
            ConfigurarAvisosTask configurarAvisosTask = new ConfigurarAvisosTask();
            configurarAvisosTask.execute((Void) null);
        }
    }

    public void setTime(String time) {
        tvTimePicker.setText(time);
    }

    public void setDate(String date) {
        tvDatePicker.setText(date);
    }

    private void setListeners() {
        tvDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        ivCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        tvTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        ivClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });
    }

    /**
     * Configurar Avisos Task
     */
    private class ConfigurarAvisosTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                //Traerme las habitaciones y pisos ocupadas
                avisosConfiguracion = dal.getAvisosDAO().getAvisosConfiguracion();
                return true;
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                rellenarCombPlantas();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
