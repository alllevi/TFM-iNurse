package tfm.muuinf.viciano.lledo.alejandro.inurse.pacientes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class MenuPacientesActivity extends AppCompatActivity implements View.OnClickListener {

    Button btRealizarSolicitud;
    Button btMisSolicitudes;
    Button btSeleccionarMenu;
    Button btMisAvisos;
    Button btInfoHospital;
    Button btCerrarSesion;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pacientes);

        initComponentes();
        setEventos();
    }

    private void initComponentes() {
        this.btRealizarSolicitud = (Button) findViewById(R.id.bt_menu_paciente_realizar_solicitud);
        this.btMisSolicitudes = (Button) findViewById(R.id.bt_menu_paciente_mis_solicitudes);
        this.btSeleccionarMenu = (Button) findViewById(R.id.bt_menu_paciente_selec_menu);
        this.btMisAvisos = (Button) findViewById(R.id.bt_menu_paciente_mis_avisos);
        this.btInfoHospital = (Button) findViewById(R.id.bt_menu_paciente_info_hosp);
        this.btCerrarSesion = (Button) findViewById(R.id.bt_menu_paciente_cerrar_sesion);
    }

    private void setEventos() {
        this.btRealizarSolicitud.setOnClickListener(this);
        this.btMisSolicitudes.setOnClickListener(this);
        this.btSeleccionarMenu.setOnClickListener(this);
        this.btMisAvisos.setOnClickListener(this);
        this.btInfoHospital.setOnClickListener(this);
        this.btCerrarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {

        if (v.getId() == R.id.bt_menu_paciente_realizar_solicitud) {
            final Intent realizarSolicitudIntent = new Intent(this, RealizarSolicitudActivity.class);
            startActivity(realizarSolicitudIntent);
        } else if (v.getId() == R.id.bt_menu_paciente_mis_solicitudes) {
            final Intent misSolictudesIntent = new Intent(this, MisSolicitudesActivity.class);
            startActivity(misSolictudesIntent);
        } else if (v.getId() == R.id.bt_menu_paciente_selec_menu) {
            final Intent seleccionMenuIntent = new Intent(this, SeleccionMenuActivity.class);
            startActivity(seleccionMenuIntent);
        } else if (v.getId() == R.id.bt_menu_paciente_mis_avisos) {
            final Intent misAvisosIntent = new Intent(this, MisAvisosActivity.class);
            startActivity(misAvisosIntent);
        } else if (v.getId() == R.id.bt_menu_paciente_info_hosp) {

        } else {

        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("¿Cerrar la aplicación?")
                .setMessage("¿Esta seguro que desea cerrar la aplicación?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(final DialogInterface arg0, final int arg1) {
                        MenuPacientesActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}
