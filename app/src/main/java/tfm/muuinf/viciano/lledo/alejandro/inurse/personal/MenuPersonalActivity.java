package tfm.muuinf.viciano.lledo.alejandro.inurse.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class MenuPersonalActivity extends AppCompatActivity implements View.OnClickListener {

    Button btSolicitudes;
    Button btMapaDeCamas;
    Button btMenus;
    Button btConfigurarAvisos;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_personal);

        initComponentes();
        setEventos();
    }

    private void setEventos() {
        this.btSolicitudes.setOnClickListener(this);
        this.btMapaDeCamas.setOnClickListener(this);
        this.btMenus.setOnClickListener(this);
        this.btConfigurarAvisos.setOnClickListener(this);
    }

    private void initComponentes() {
        this.btSolicitudes = (Button) findViewById(R.id.bt_menu_personal_solicitudes);
        this.btMapaDeCamas = (Button) findViewById(R.id.bt_menu_personal_mapa);
        this.btMenus = (Button) findViewById(R.id.bt_menu_personal_menus);
        this.btConfigurarAvisos = (Button) findViewById(R.id.bt_menu_personal_configurar);
    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.bt_menu_personal_solicitudes) {
            final Intent listarSolicitudIntent = new Intent(this, ListarSolicitudesActivity.class);
            startActivity(listarSolicitudIntent);
        } else if (v.getId() == R.id.bt_menu_personal_mapa) {

        } else if (v.getId() == R.id.bt_menu_personal_menus) {
            final Intent listarMenusIntent = new Intent(this, ListarMenusActivity.class);
            startActivity(listarMenusIntent);
        } else {
            final Intent configurarAvisosIntent = new Intent(this, ConfigurarAvisosActivity.class);
            startActivity(configurarAvisosIntent);
        }
    }

    @Override
    public void onBackPressed() {
        // Boton atras
    }
}
