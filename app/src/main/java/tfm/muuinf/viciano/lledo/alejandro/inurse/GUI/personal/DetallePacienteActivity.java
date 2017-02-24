package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.PacienteDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class DetallePacienteActivity extends InurseActivity {

    private Integer pacienteKey;
    private ImageView imagenPaciente;
    private TextView habitacion;
    private TextView nombre;
    private TextView apellidos;
    private TextView sexo;
    private TextView edad;
    private TextView sip;
    private TextView nhc;
    private TextView motivoIngreso;
    private TextView telefono;
    private TextView direccion;
    private TextView ciudad;
    private TextView provincia;
    private TextView nacionalidad;
    private PacienteDTO pacienteDTO;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_paciente);
        pacienteKey = getIntent().getExtras().getInt("pacienteKey");
        initComponentes();
        initTask();
    }

    private void initComponentes() {
        imagenPaciente = (ImageView) findViewById(R.id.iv_detalle_imagen);
        habitacion = (TextView) findViewById(R.id.tv_detalle_habitacion);
        nombre = (TextView) findViewById(R.id.tv_detalle_nombre);
        apellidos = (TextView) findViewById(R.id.tv_detalle_apellidos);
        sexo = (TextView) findViewById(R.id.tv_detalle_sexo);
        edad = (TextView) findViewById(R.id.tv_detalle_edad);
        sip = (TextView) findViewById(R.id.tv_detalle_sip);
        nhc = (TextView) findViewById(R.id.tv_detalle_nhc);
        motivoIngreso = (TextView) findViewById(R.id.tv_detalle_motivo);
        telefono = (TextView) findViewById(R.id.tv_detalle_telefono);
        direccion = (TextView) findViewById(R.id.tv_detalle_direccion);
        ciudad = (TextView) findViewById(R.id.tv_detalle_ciudad);
        provincia = (TextView) findViewById(R.id.tv_detalle_provincia);
        nacionalidad = (TextView) findViewById(R.id.tv_detalle_nacionalidad);
    }

    private void setValores() {
        setIcon();
        habitacion.setText(pacienteDTO.getHabitacion());
        nombre.setText(pacienteDTO.getNombre());
        apellidos.setText(pacienteDTO.getApellidos());
        sexo.setText(pacienteDTO.getSexo());
        edad.setText(pacienteDTO.getEdad());
        sip.setText(pacienteDTO.getSip());
        nhc.setText(pacienteDTO.getNhc());
        motivoIngreso.setText(pacienteDTO.getMotivoIngreso());
        telefono.setText(pacienteDTO.getTelefono());
        direccion.setText(pacienteDTO.getDireccion());
        ciudad.setText(pacienteDTO.getCiudad());
        provincia.setText(pacienteDTO.getProvincia());
        nacionalidad.setText(pacienteDTO.getNacionalidad());
    }

    private void setIcon() {
        String sexoPaci = pacienteDTO.getSexo();
        Integer edadPaci = Integer.parseInt(pacienteDTO.getEdad());
        if ("Hombre".equals(sexoPaci)) {
            if (edadPaci <= 1) {
                imagenPaciente.setImageResource(R.drawable.babyboy);
            } else if (1 < edadPaci && edadPaci < 18) {
                imagenPaciente.setImageResource(R.drawable.boy);
            } else if (18 <= edadPaci && edadPaci < 65) {
                imagenPaciente.setImageResource(R.drawable.male);
            } else {
                imagenPaciente.setImageResource(R.drawable.oldman);
            }
        } else {
            if (edadPaci <= 1) {
                imagenPaciente.setImageResource(R.drawable.babygirl);
            } else if (1 < edadPaci && edadPaci < 18) {
                imagenPaciente.setImageResource(R.drawable.girl);
            } else if (18 <= edadPaci && edadPaci < 65) {
                imagenPaciente.setImageResource(R.drawable.female);
            } else {
                imagenPaciente.setImageResource(R.drawable.oldwoman);
            }
        }
    }

    private void initTask() {
        if (checkInternet()) {
            DetallePacienteTask detallePacienteTask = new DetallePacienteTask();
            detallePacienteTask.execute((Void) null);
        }
    }

    /**
     * Detalle paciente task
     */
    private class DetallePacienteTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                pacienteDTO = dal.getPacienteDAO().getDetalle(pacienteKey);
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                setValores();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
