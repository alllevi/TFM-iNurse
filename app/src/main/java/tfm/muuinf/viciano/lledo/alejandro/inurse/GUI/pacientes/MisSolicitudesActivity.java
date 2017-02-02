package tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.pacientes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.adaptadores.AdapterMisSolicitudes;
import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class MisSolicitudesActivity extends AppCompatActivity {

    private Spinner comboEstados;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_solicitudes);
        initComponents();
        rellenarComboEstados();
        rellenarRecyclerView();
    }

    private void initComponents() {
        comboEstados = (Spinner) findViewById(R.id.cb_filtro_mis_solicitudes);
        recyclerView = (RecyclerView) findViewById(R.id.rv_mis_solicitudes);
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        final AdapterMisSolicitudes adapter = new AdapterMisSolicitudes();
        recyclerView.setAdapter(adapter);
    }

    private void rellenarComboEstados() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboEstados.setAdapter(adapter);
    }
}