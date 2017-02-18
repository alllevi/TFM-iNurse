package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.pacientes;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.ConstantesGUI;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.LoginActivity;

public class MenuPacientesActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedpreferences;
    private Button btRealizarSolicitud;
    private Button btMisSolicitudes;
    private Button btSeleccionarMenu;
    private Button btMisAvisos;
    private Button btInfoHospital;
    private Button btCerrarSesion;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pacientes);

        initComponentes();
        setEventos();
    }

    private void initComponentes() {
        sharedpreferences = getSharedPreferences(ConstantesGUI.SHARED_PREFS_FILE, ConstantesGUI.CONTEXT_MODE_PRIVATE);
        btRealizarSolicitud = (Button) findViewById(R.id.bt_menu_paciente_realizar_solicitud);
        btMisSolicitudes = (Button) findViewById(R.id.bt_menu_paciente_mis_solicitudes);
        btSeleccionarMenu = (Button) findViewById(R.id.bt_menu_paciente_selec_menu);
        btMisAvisos = (Button) findViewById(R.id.bt_menu_paciente_mis_avisos);
        btInfoHospital = (Button) findViewById(R.id.bt_menu_paciente_info_hosp);
        btCerrarSesion = (Button) findViewById(R.id.bt_menu_paciente_cerrar_sesion);
    }

    private void setEventos() {
        btRealizarSolicitud.setOnClickListener(this);
        btMisSolicitudes.setOnClickListener(this);
        btSeleccionarMenu.setOnClickListener(this);
        btMisAvisos.setOnClickListener(this);
        btInfoHospital.setOnClickListener(this);
        btCerrarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {

        if (v.getId() == R.id.bt_menu_paciente_realizar_solicitud) {
            Intent realizarSolicitudIntent = new Intent(this, RealizarSolicitudActivity.class);
            startActivity(realizarSolicitudIntent);
        } else if (v.getId() == R.id.bt_menu_paciente_mis_solicitudes) {
            Intent misSolictudesIntent = new Intent(this, MisSolicitudesActivity.class);
            startActivity(misSolictudesIntent);
        } else if (v.getId() == R.id.bt_menu_paciente_selec_menu) {
            Intent seleccionMenuIntent = new Intent(this, SeleccionMenuActivity.class);
            startActivity(seleccionMenuIntent);
        } else if (v.getId() == R.id.bt_menu_paciente_mis_avisos) {
            Intent misAvisosIntent = new Intent(this, MisAvisosActivity.class);
            startActivity(misAvisosIntent);
        } else if (v.getId() == R.id.bt_menu_paciente_info_hosp) {
            Intent infoHospitalIntent = new Intent(this, InfoHospitalActivity.class);
            startActivity(infoHospitalIntent);
        } else {
            eventoCerrarSesion();
        }
    }

    private void eventoCerrarSesion() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MenuPacientesActivity.this);

        builder.setMessage("¿Quiere cerrar su sesión?")
                .setTitle("Salir");

        builder.setPositiveButton("Cerrar sesión", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                // Cerramos la sesion
                cerrarSesion();

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                //No hacemos nada
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void cerrarSesion() {
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(ConstantesGUI.USUARIO_KEY, "");
        editor.putString(ConstantesGUI.PACIENTE_KEY, "");
        editor.apply();
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
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
