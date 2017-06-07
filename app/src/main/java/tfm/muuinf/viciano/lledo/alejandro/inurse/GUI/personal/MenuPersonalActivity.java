package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.ConstantesComun;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.LoginActivity;

public class MenuPersonalActivity extends InurseActivity implements View.OnClickListener {

    private SharedPreferences sharedpreferences;
    private Button btSolicitudes;
    private Button btMapaDeCamas;
    private Button btMenus;
    private Button btVisualizarAvisos;
    private Button btConfigurarAvisos;
    private Button btCerrarSesion;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_personal);

        initComponentes();
        setEventos();
    }

    private void initComponentes() {
        sharedpreferences = getSharedPreferences(ConstantesComun.SHARED_PREFS_FILE, ConstantesComun.CONTEXT_MODE_PRIVATE);
        btSolicitudes = (Button) findViewById(R.id.bt_menu_personal_solicitudes);
        btMapaDeCamas = (Button) findViewById(R.id.bt_menu_personal_mapa);
        btMenus = (Button) findViewById(R.id.bt_menu_personal_menus);
        btVisualizarAvisos = (Button) findViewById(R.id.bt_menu_personal_avisos);
        btConfigurarAvisos = (Button) findViewById(R.id.bt_menu_personal_configurar);
        btCerrarSesion = (Button) findViewById(R.id.bt_menu_personal_cerrar_sesion);
    }

    private void setEventos() {
        btSolicitudes.setOnClickListener(this);
        btMapaDeCamas.setOnClickListener(this);
        btMenus.setOnClickListener(this);
        btVisualizarAvisos.setOnClickListener(this);
        btConfigurarAvisos.setOnClickListener(this);
        btCerrarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.bt_menu_personal_solicitudes) {
            final Intent listarSolicitudIntent = new Intent(this, ListarSolicitudesActivity.class);
            startActivity(listarSolicitudIntent);
        } else if (v.getId() == R.id.bt_menu_personal_mapa) {
            final Intent mapaCamasIntent = new Intent(this, MapaHospitalarioActivity.class);
            startActivity(mapaCamasIntent);
        } else if (v.getId() == R.id.bt_menu_personal_avisos) {
            final Intent listarAvisosActivosIntent = new Intent(this, ListarAvisosActivos.class);
            startActivity(listarAvisosActivosIntent);
        } else if (v.getId() == R.id.bt_menu_personal_menus) {
            final Intent listarMenusIntent = new Intent(this, ListarMenusActivity.class);
            startActivity(listarMenusIntent);
        } else if (v.getId() == R.id.bt_menu_personal_configurar) {
            final Intent configurarAvisosIntent = new Intent(this, ConfigurarAvisosActivity.class);
            startActivity(configurarAvisosIntent);
        } else {
            eventoCerrarSesion();
        }
    }

    private void eventoCerrarSesion() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MenuPersonalActivity.this);

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
        editor.putString(ConstantesComun.USUARIO_KEY, "");
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
                        MenuPersonalActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}
