package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.tuple.Pair;

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
    private Integer pacienteSelected;
    private Spinner cbPlantas;
    private Spinner cbHabitacion;
    private TextView tvPaciente;
    private CheckBox chRepetir;
    private TextView etHoras;
    private TextView etDescripcion;
    private Button btProgramar;
    private TextView tvDatePicker;
    private TextView tvTimePicker;
    private ImageView ivCalendar;
    private ImageView ivClock;
    private TextView tvHora;
    private TextView tvRepetirCada;
    private TextView tvDatePickerFin;
    private ImageView ivCalendarFin;

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
        etHoras = (TextView) findViewById(R.id.et_avisos_horas);
        etDescripcion = (TextView) findViewById(R.id.et_avisos_descripcion);
        btProgramar = (Button) findViewById(R.id.bt_avisos_programar);
        tvDatePicker = (TextView) findViewById(R.id.tv_avisos_date_picker);
        tvTimePicker = (TextView) findViewById(R.id.tv_avisos_time_picker);
        ivCalendar = (ImageView) findViewById(R.id.iv_avisos_calendar);
        ivClock = (ImageView) findViewById(R.id.iv_avisos_clock);
        tvHora = (TextView) findViewById(R.id.tv_avisos_hora);
        tvRepetirCada = (TextView) findViewById(R.id.tv_avisos_repetir_cada);
        tvDatePickerFin = (TextView) findViewById(R.id.tv_avisos_date_picker_fin);
        ivCalendarFin = (ImageView) findViewById(R.id.iv_avisos_calendar_fin);
        cambiarEstadoEtHoras(false);
    }

    private void cambiarEstadoEtHoras(boolean activo) {
        if (activo) {
            etHoras.setEnabled(true);
            tvRepetirCada.setTextColor(Color.BLACK);
            tvHora.setTextColor(Color.BLACK);
        } else {
            etHoras.setEnabled(false);
            tvRepetirCada.setTextColor(Color.GRAY);
            tvHora.setTextColor(Color.GRAY);
        }
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
                Pair<Integer, String> pairPaciente = avisosConfiguracion.getMapPacientes().get(key);
                tvPaciente.setText(pairPaciente.getRight());
                pacienteSelected = pairPaciente.getLeft();
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

    public void setDate(String date, String tag) {
        if ("datePicker".equals(tag)) {
            tvDatePicker.setText(date);
        } else {
            tvDatePickerFin.setText(date);
        }
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

        tvDatePickerFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePickerFin");
            }
        });

        ivCalendarFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePickerFin");
            }
        });

        //Permite que al crear la actividad el editText no gane el foco
        etHoras.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });

        etDescripcion.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });

        chRepetir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cambiarEstadoEtHoras(isChecked);
            }
        });

        btProgramar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarAviso();
            }
        });

    }

    private void insertarAviso() {
        String fechaIni = tvDatePicker.getText().toString();
        String horaIni = tvTimePicker.getText().toString();
        String fechaFin = tvDatePickerFin.getText().toString();
        String repetirHoras = etHoras.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        if (checkInternet()) {
            InsertarAvisosTask insertarAvisosTask = new InsertarAvisosTask(fechaIni, horaIni, fechaFin, repetirHoras, descripcion);
            insertarAvisosTask.execute((Void) null);
        }
    }

    private void clearComponentes() {
        tvDatePicker.setText("");
        tvTimePicker.setText("");
        tvDatePickerFin.setText("");
        etHoras.setText("");
        etDescripcion.setText("");
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

    /**
     * Insertar Avisos Task
     */
    private class InsertarAvisosTask extends AsyncTask<Void, Void, Boolean> {

        private String fechaIni;
        private String horaIni;
        private String fechaFin;
        private String repetirHoras;
        private String descripcion;

        public InsertarAvisosTask(String fechaIni, String horaIni, String fechaFin, String repetirHoras, String descripcion) {
            this.fechaIni = fechaIni;
            this.horaIni = horaIni;
            this.fechaFin = fechaFin;
            this.repetirHoras = repetirHoras;
            this.descripcion = descripcion;
        }

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                return dal.getAvisosDAO().insertarAviso(fechaIni, horaIni, fechaFin, repetirHoras, descripcion, pacienteSelected);
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(getApplicationContext(), "Se ha programado el aviso correctamente", Toast.LENGTH_SHORT).show();
                clearComponentes();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
