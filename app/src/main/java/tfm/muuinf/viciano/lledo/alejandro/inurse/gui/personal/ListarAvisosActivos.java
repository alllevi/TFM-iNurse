package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

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
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.AvisosDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores.AdapterListarAvisosActivos;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class ListarAvisosActivos extends InurseActivity {

    private Spinner cbPlantas;
    private RecyclerView recyclerView;
    private Integer maxPlanta;
    private List<AvisosDTO> listaAvisos;
    private Map<Integer, List<AvisosDTO>> hashAvisosPlantas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_avisos_activos);

        initComponentes();
        initTask();
        setListeners();
    }

    private void initComponentes() {
        cbPlantas = (Spinner) findViewById(R.id.cb_filtro_plantas_avisos);
        recyclerView = (RecyclerView) findViewById(R.id.rv_listar_avisos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
    }

    private void initTask() {
        if (checkInternet()) {
            ListarAvisosTask listarAvisosTask = new ListarAvisosTask();
            listarAvisosTask.execute((Void) null);
        }
    }

    private void rellenarRecyclerView(List<AvisosDTO> listaMenus) {
        AdapterListarAvisosActivos adapter = new AdapterListarAvisosActivos(listaMenus, this);
        recyclerView.setAdapter(adapter);
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

    private void setListeners() {
        cbPlantas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenarRecyclerView(hashAvisosPlantas.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    private void crearHashPlantas() {
        hashAvisosPlantas = new HashMap<>();
        hashAvisosPlantas.put(0, listaAvisos);
        for (AvisosDTO avisoDTO : listaAvisos) {
            Integer planta = avisoDTO.getPlanta();
            if (!hashAvisosPlantas.containsKey(planta)) {
                List<AvisosDTO> listaMenu = new ArrayList<>();
                listaMenu.add(avisoDTO);
                hashAvisosPlantas.put(planta, listaMenu);
            } else {
                hashAvisosPlantas.get(planta).add(avisoDTO);
            }
        }

        //Aseguramos que la hash no tenga ninguna planta a null
        for (int i = 0; i <= maxPlanta; i++) {
            if (!hashAvisosPlantas.containsKey(i)) {
                hashAvisosPlantas.put(i, new ArrayList<AvisosDTO>());
            }
        }
    }

    /**
     * Listar menus task
     */
    private class ListarAvisosTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                listaAvisos = dal.getAvisosDAO().getAvisosActivos();
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
                rellenarRecyclerView(listaAvisos);
                rellenarComboPlantas();
                crearHashPlantas();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
