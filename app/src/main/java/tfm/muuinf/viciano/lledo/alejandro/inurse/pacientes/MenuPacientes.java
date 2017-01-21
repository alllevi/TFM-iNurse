package tfm.muuinf.viciano.lledo.alejandro.inurse.pacientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class MenuPacientes extends AppCompatActivity implements View.OnClickListener {

    Button btRealizarSolicitud;
    Button btMisSolicitudes;
    Button btSeleccionarMenu;
    Button btMisAvisos;
    Button btInfoHospital;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pacientes);

        initComponentes();
        setEventos();
    }

    private void initComponentes() {
        this.btRealizarSolicitud = (Button) findViewById(R.id.btMenuPacienteRealizarSolicitud);
        this.btMisSolicitudes = (Button) findViewById(R.id.btMenuPacienteMisSolicitudes);
        this.btSeleccionarMenu = (Button) findViewById(R.id.btMenuPacienteSelecMenu);
        this.btMisAvisos = (Button) findViewById(R.id.btMenuPacienteMisAvisos);
        this.btInfoHospital = (Button) findViewById(R.id.btMenuPacienteInfoHosp);
    }

    private void setEventos() {
        this.btRealizarSolicitud.setOnClickListener(this);
        this.btMisSolicitudes.setOnClickListener(this);
        this.btSeleccionarMenu.setOnClickListener(this);
        this.btMisAvisos.setOnClickListener(this);
        this.btInfoHospital.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {

        if (v.getId() == R.id.btMenuPacienteRealizarSolicitud) {
            final Intent realizarSolicitudIntent = new Intent(this, RealizarSolicitud.class);
            startActivity(realizarSolicitudIntent);
        } else if (v.getId() == R.id.btMenuPacienteMisSolicitudes) {
            final Intent misSolictudesIntent = new Intent(this, MisSolicitudes.class);
            startActivity(misSolictudesIntent);
        } else if (v.getId() == R.id.btMenuPacienteSelecMenu) {
            final Intent seleccionMenuIntent = new Intent(this, SeleccionMenu.class);
            startActivity(seleccionMenuIntent);
        } else if (v.getId() == R.id.btMenuPacienteMisAvisos) {
            final Intent misAvisosIntent = new Intent(this, MisAvisos.class);
            startActivity(misAvisosIntent);
        } else {

        }
    }

    @Override
    public void onBackPressed() {
        // Boton atras
    }
}
