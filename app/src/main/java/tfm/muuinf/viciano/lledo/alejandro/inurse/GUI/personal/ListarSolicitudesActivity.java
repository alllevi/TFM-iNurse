package tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.adaptadores.AdapterListarSolicitudes;
import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class ListarSolicitudesActivity extends AppCompatActivity {

    Spinner cbEstados;
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
        this.cbEstados = (Spinner) findViewById(R.id.cb_filtro_listar_solicitudes);
        this.recyclerView = (RecyclerView) findViewById(R.id.rv_listar_solicitudes);
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(llm);
        final AdapterListarSolicitudes adapter = new AdapterListarSolicitudes(ListarSolicitudesActivity.this);
        this.recyclerView.setAdapter(adapter);
    }

    private void rellenarComboEstados() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.cbEstados.setAdapter(adapter);
    }

    public void onClickRechazar() {
        final Intent RechazarSolicitudIntent = new Intent(this, RechazarSolicitudActivity.class);
        startActivity(RechazarSolicitudIntent);
    }

    @Override
    public void onBackPressed() {
        // Boton atras
    }
}
