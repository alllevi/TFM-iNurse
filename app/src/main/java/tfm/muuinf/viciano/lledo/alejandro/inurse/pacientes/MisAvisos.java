package tfm.muuinf.viciano.lledo.alejandro.inurse.pacientes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.adapters.AdapterMisAvisos;

public class MisAvisos extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tv_fecha;
    TextView tv_frecuencia;
    TextView tv_descripcion;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_avisos);

        initComponentes();
        rellenarRecyclerView();
    }

    private void initComponentes() {
        this.recyclerView = (RecyclerView) findViewById(R.id.rv_mis_avisos);
        this.tv_fecha = (TextView) findViewById(R.id.tv_mis_avisos_fecha);
        this.tv_frecuencia = (TextView) findViewById(R.id.tv_mis_avisos_frecuencia);
        this.tv_descripcion = (TextView) findViewById(R.id.tv_mis_avisos_descripcion);
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(llm);
        final AdapterMisAvisos adapter = new AdapterMisAvisos();
        this.recyclerView.setAdapter(adapter);
    }
}
