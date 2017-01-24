package tfm.muuinf.viciano.lledo.alejandro.inurse.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.adapters.AdapterMapaCamas;

public class MapaCamasActivity extends AppCompatActivity {

    Spinner cbPlantas;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_camas);

        initComponentes();
        rellenarComboPlantas();
        rellenarRecyclerView();
    }

    private void initComponentes() {
        this.cbPlantas = (Spinner) findViewById(R.id.cb_filtro_plantas_mapa_camas);
        this.recyclerView = (RecyclerView) findViewById(R.id.rv_mapa_camas);
    }

    private void rellenarComboPlantas() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plantas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.cbPlantas.setAdapter(adapter);
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(llm);
        final AdapterMapaCamas adapter = new AdapterMapaCamas(MapaCamasActivity.this);
        this.recyclerView.setAdapter(adapter);
    }

    public void onClickCard() {
        final Intent detallePacienteIntent = new Intent(this, DetallePacienteActivity.class);
        startActivity(detallePacienteIntent);
    }

    @Override
    public void onBackPressed() {
        // Boton atras
    }
}
