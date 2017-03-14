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
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MenuDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores.AdapterListarMenus;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class ListarMenusActivity extends InurseActivity {

    private Spinner cbPlantas;
    private RecyclerView recyclerView;
    private Integer maxPlanta;
    private List<MenuDTO> listaMenus;
    private Map<Integer, List<MenuDTO>> hashMenuPlantas;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_menus);

        initComponentes();
        initTask();
        setListeners();
    }

    private void initComponentes() {
        cbPlantas = (Spinner) findViewById(R.id.cb_filtro_plantas_menu);
        recyclerView = (RecyclerView) findViewById(R.id.rv_listar_menus);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
    }

    private void initTask() {
        if (checkInternet()) {
            ListarMenusTask solicitudesTask = new ListarMenusTask();
            solicitudesTask.execute((Void) null);
        }
    }

    private void rellenarRecyclerView(List<MenuDTO> listaMenus) {
        AdapterListarMenus adapter = new AdapterListarMenus(listaMenus);
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
                rellenarRecyclerView(hashMenuPlantas.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void crearHashPlantas() {
        hashMenuPlantas = new HashMap<>();
        hashMenuPlantas.put(0, listaMenus);
        for (MenuDTO menuDTO : listaMenus) {
            Integer planta = menuDTO.getPlanta();
            if (!hashMenuPlantas.containsKey(planta)) {
                List<MenuDTO> listaMenu = new ArrayList<>();
                listaMenu.add(menuDTO);
                hashMenuPlantas.put(planta, listaMenu);
            } else {
                hashMenuPlantas.get(planta).add(menuDTO);
            }
        }

        //Aseguramos que la hash no tenga ninguna planta a null
        for (int i = 0; i <= maxPlanta; i++) {
            if (!hashMenuPlantas.containsKey(i)) {
                hashMenuPlantas.put(i, new ArrayList<MenuDTO>());
            }
        }
    }

    /**
     * Listar menus task
     */
    private class ListarMenusTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                listaMenus = dal.getMenusDAO().getListMenus();
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
                rellenarRecyclerView(listaMenus);
                rellenarComboPlantas();
                crearHashPlantas();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
