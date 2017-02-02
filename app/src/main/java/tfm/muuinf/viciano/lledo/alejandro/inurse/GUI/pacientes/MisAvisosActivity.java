package tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.pacientes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.adaptadores.AdapterMisAvisos;
import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class MisAvisosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tv_fecha;
    private TextView tv_frecuencia;
    private TextView tv_descripcion;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_avisos);

        initComponentes();
        rellenarRecyclerView();
    }

    private void initComponentes() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_mis_avisos);
        tv_fecha = (TextView) findViewById(R.id.tv_mis_avisos_fecha);
        tv_frecuencia = (TextView) findViewById(R.id.tv_mis_avisos_frecuencia);
        tv_descripcion = (TextView) findViewById(R.id.tv_mis_avisos_descripcion);
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        final AdapterMisAvisos adapter = new AdapterMisAvisos();
        recyclerView.setAdapter(adapter);
    }
}
