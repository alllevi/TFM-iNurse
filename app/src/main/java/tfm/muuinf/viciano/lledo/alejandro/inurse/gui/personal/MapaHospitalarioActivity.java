package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dao.MapaHospitalarioDAO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MapaHospitalarioDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores.AdapterMapaHospitalario;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class MapaHospitalarioActivity extends InurseActivity {

    private Spinner cbPlantas;
    private RecyclerView recyclerView;
    private List<MapaHospitalarioDTO> listaMapaHospitalario;
    private Integer maxPlanta;
    private Map<Integer, List<MapaHospitalarioDTO>> hashMapaPlantas;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_hospitalario);

        initComponentes();
        initTask();
    }

    private void initComponentes() {
        cbPlantas = (Spinner) findViewById(R.id.cb_filtro_plantas_mapa_camas);
        recyclerView = (RecyclerView) findViewById(R.id.rv_mapa_camas);
    }

    private void rellenarComboPlantas() {
        List<String> arrayPlantas = new ArrayList<>();
        arrayPlantas.add("Todas");
        for (int i = 1; i <= maxPlanta; i++) {
            arrayPlantas.add("Planta " + i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayPlantas);
        cbPlantas.setAdapter(arrayAdapter);
    }

    private void rellenarRecyclerView(List<MapaHospitalarioDTO> listaMapaHospitalario) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        AdapterMapaHospitalario adapter = new AdapterMapaHospitalario(MapaHospitalarioActivity.this, listaMapaHospitalario);
        recyclerView.setAdapter(adapter);
    }

    private void setListeners() {
        cbPlantas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenarRecyclerView(hashMapaPlantas.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void crearHashPlantas() {
        hashMapaPlantas = new HashMap<>();
        hashMapaPlantas.put(0, listaMapaHospitalario);
        for (MapaHospitalarioDTO mapaHospitalario : listaMapaHospitalario) {
            Integer planta = mapaHospitalario.getPlanta();
            if (!hashMapaPlantas.containsKey(planta)) {
                List<MapaHospitalarioDTO> listaMapa = new ArrayList<>();
                listaMapa.add(mapaHospitalario);
                hashMapaPlantas.put(planta, listaMapa);
            } else {
                hashMapaPlantas.get(planta).add(mapaHospitalario);
            }
        }

        //Aseguramos que la hash no tenga ninguna planta a null
        for (int i = 0; i <= maxPlanta; i++) {
            if (!hashMapaPlantas.containsKey(i)) {
                hashMapaPlantas.put(i, new ArrayList<MapaHospitalarioDTO>());
            }
        }
    }

    private void initTask() {
        if (checkInternet()) {
            MapaCamasTask solicitudesTask = new MapaCamasTask();
            solicitudesTask.execute((Void) null);
        }
    }

    public void onClickCard(Integer pacienteKey) {
        final Intent detallePacienteIntent = new Intent(this, DetallePacienteActivity.class);
        detallePacienteIntent.putExtra("pacienteKey", pacienteKey);
        startActivity(detallePacienteIntent);
    }

    /**
     * Mapa camas task
     */
    private class MapaCamasTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            MapaHospitalarioDAO mapaHospitalarioDAO = dal.getMapaHospitalarioDAO();
            try {
                listaMapaHospitalario = mapaHospitalarioDAO.getMapaHospitalario();
                maxPlanta = dal.getMapaHospitalarioDAO().getMaxPlanta();
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                rellenarRecyclerView(listaMapaHospitalario);
                rellenarComboPlantas();
                crearHashPlantas();
                setListeners();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
