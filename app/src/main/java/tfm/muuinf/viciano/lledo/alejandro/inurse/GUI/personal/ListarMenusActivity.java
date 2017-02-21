package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class ListarMenusActivity extends InurseActivity {

    private Spinner cbPlantas;
    private ListView lvListaMenus;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_menus);

        initComponentes();
        rellenarComboPlantas();
    }

    private void initComponentes() {
        cbPlantas = (Spinner) findViewById(R.id.cb_filtro_plantas_menu);
        lvListaMenus = (ListView) findViewById(R.id.lv_menus_plantas);
    }

    private void rellenarComboPlantas() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plantas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbPlantas.setAdapter(adapter);
    }
    
}
