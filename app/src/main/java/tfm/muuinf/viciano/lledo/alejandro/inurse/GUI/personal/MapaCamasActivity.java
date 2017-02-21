package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores.AdapterMapaCamas;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class MapaCamasActivity extends InurseActivity {

    private Spinner cbPlantas;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_camas);

        initComponentes();
        rellenarComboPlantas();
        rellenarRecyclerView();
    }

    private void initComponentes() {
        cbPlantas = (Spinner) findViewById(R.id.cb_filtro_plantas_mapa_camas);
        recyclerView = (RecyclerView) findViewById(R.id.rv_mapa_camas);
    }

    private void rellenarComboPlantas() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plantas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbPlantas.setAdapter(adapter);
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        final AdapterMapaCamas adapter = new AdapterMapaCamas(MapaCamasActivity.this);
        recyclerView.setAdapter(adapter);
    }

    public void onClickCard() {
        final Intent detallePacienteIntent = new Intent(this, DetallePacienteActivity.class);
        startActivity(detallePacienteIntent);
    }
    
}
