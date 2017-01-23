package tfm.muuinf.viciano.lledo.alejandro.inurse.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Spinner;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class ListarMenusActivity extends AppCompatActivity {

    Spinner cbPlantas;
    ListView lvListaMenus;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_menus);

        initComponentes();
    }

    private void initComponentes() {
        this.cbPlantas = (Spinner) findViewById(R.id.cb_filtro_plantas_menu);
        this.lvListaMenus = (ListView) findViewById(R.id.lv_menus_plantas);
    }
}
