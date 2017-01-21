package tfm.muuinf.viciano.lledo.alejandro.inurse.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.adapters.AdapterListarSolicitudes;

public class ListarSolicitudes extends AppCompatActivity {

    Spinner comboEstados;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_solicitudes);

        initComponentes();
        rellenarComboEstados();
        rellenarRecyclerView();
    }

    private void initComponentes() {
        this.comboEstados = (Spinner) findViewById(R.id.cb_filtro_listar_solicitudes);
        this.recyclerView = (RecyclerView) findViewById(R.id.rv_listar_solicitudes);
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(llm);
        final AdapterListarSolicitudes adapter = new AdapterListarSolicitudes();
        this.recyclerView.setAdapter(adapter);
    }

    private void rellenarComboEstados() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.comboEstados.setAdapter(adapter);
    }
}
