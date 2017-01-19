package tfm.muuinf.viciano.lledo.alejandro.inurse.pacientes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.adapters.AdapterSeleccionMenus;

public class SeleccionMenu extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_menu);

        this.recyclerView = (RecyclerView) findViewById(R.id.rv_seleccion_menu);
        rellenarRecyclerView();
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(llm);
        final AdapterSeleccionMenus adapter = new AdapterSeleccionMenus();
        this.recyclerView.setAdapter(adapter);
    }
}
