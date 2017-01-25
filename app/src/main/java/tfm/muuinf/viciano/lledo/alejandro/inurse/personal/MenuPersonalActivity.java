package tfm.muuinf.viciano.lledo.alejandro.inurse.personal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class MenuPersonalActivity extends AppCompatActivity implements View.OnClickListener {

    Button btSolicitudes;
    Button btMapaDeCamas;
    Button btMenus;
    Button btConfigurarAvisos;
    Button btCerrarSesion;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_personal);

        initComponentes();
        setEventos();
    }

    private void initComponentes() {
        this.btSolicitudes = (Button) findViewById(R.id.bt_menu_personal_solicitudes);
        this.btMapaDeCamas = (Button) findViewById(R.id.bt_menu_personal_mapa);
        this.btMenus = (Button) findViewById(R.id.bt_menu_personal_menus);
        this.btConfigurarAvisos = (Button) findViewById(R.id.bt_menu_personal_configurar);
        this.btCerrarSesion = (Button) findViewById(R.id.bt_menu_personal_cerrar_sesion);
    }

    private void setEventos() {
        this.btSolicitudes.setOnClickListener(this);
        this.btMapaDeCamas.setOnClickListener(this);
        this.btMenus.setOnClickListener(this);
        this.btConfigurarAvisos.setOnClickListener(this);
        this.btCerrarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.bt_menu_personal_solicitudes) {
            final Intent listarSolicitudIntent = new Intent(this, ListarSolicitudesActivity.class);
            startActivity(listarSolicitudIntent);
        } else if (v.getId() == R.id.bt_menu_personal_mapa) {
            final Intent mapaCamasIntent = new Intent(this, MapaCamasActivity.class);
            startActivity(mapaCamasIntent);
        } else if (v.getId() == R.id.bt_menu_personal_menus) {
            final Intent listarMenusIntent = new Intent(this, ListarMenusActivity.class);
            startActivity(listarMenusIntent);
        } else if (v.getId() == R.id.bt_menu_personal_configurar) {
            final Intent configurarAvisosIntent = new Intent(this, ConfigurarAvisosActivity.class);
            startActivity(configurarAvisosIntent);
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
                        MenuPersonalActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}
