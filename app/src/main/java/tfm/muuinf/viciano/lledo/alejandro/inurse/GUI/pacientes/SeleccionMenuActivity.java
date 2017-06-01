package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.pacientes;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MenuDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores.AdapterSeleccionMenus;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.ConstantesComun;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class SeleccionMenuActivity extends InurseActivity {

    private RecyclerView recyclerView;
    private List<MenuDTO> listaMenus;
    private Integer itemCountSelected;
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_menu);
        initComponentes();
        initTask();
    }

    private void initComponentes() {
        itemCountSelected = 0;
        recyclerView = (RecyclerView) findViewById(R.id.rv_seleccion_menu);
        sharedpreferences = getSharedPreferences(ConstantesComun.SHARED_PREFS_FILE, ConstantesComun.CONTEXT_MODE_PRIVATE);
    }

    private void rellenarRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        AdapterSeleccionMenus adapter = new AdapterSeleccionMenus(SeleccionMenuActivity.this, listaMenus);
        recyclerView.setAdapter(adapter);
    }

    private void initTask() {
        if (checkInternet()) {
            MenusTask menusTask = new MenusTask();
            menusTask.execute((Void) null);
        }
    }

    public Boolean controlCardViewItems(Integer suma) {
        itemCountSelected = itemCountSelected + suma;
        if (itemCountSelected > 1) {
            itemCountSelected = 1;
            Toast.makeText(getApplicationContext(), "No puede seleccionar más de un menú", Toast.LENGTH_SHORT).show();
            return false;
        } else return true;
    }

    public void seleccionarMenu(MenuDTO menuDTO) {
        if (checkInternet()) {
            SeleccionarMenuTask seleccionarMenuTask = new SeleccionarMenuTask(menuDTO);
            seleccionarMenuTask.execute((Void) null);
        }
    }

    public void deseleccionarMenu(MenuDTO menuDTO) {
        if (checkInternet()) {
            DeseleccionarMenuTask deseleccionarMenuTask = new DeseleccionarMenuTask(menuDTO);
            deseleccionarMenuTask.execute((Void) null);
        }
    }

    /**
     * Get menus del dia
     */
    private class MenusTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                listaMenus = dal.getMenusDAO().getMenus(sharedpreferences.getString(ConstantesComun.PACIENTE_KEY, ""));
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                rellenarRecyclerView();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Seleccionar menu
     */
    private class SeleccionarMenuTask extends AsyncTask<Void, Void, Boolean> {

        private MenuDTO menuDTO;

        public SeleccionarMenuTask(MenuDTO menuDTO) {
            this.menuDTO = menuDTO;
        }

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                return dal.getMenuDAO().insertarMenu(menuDTO, sharedpreferences.getString(ConstantesComun.PACIENTE_KEY, ""));
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Borrar menu
     */
    private class DeseleccionarMenuTask extends AsyncTask<Void, Void, Boolean> {

        private MenuDTO menuDTO;

        public DeseleccionarMenuTask(MenuDTO menuDTO) {
            this.menuDTO = menuDTO;
        }

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                return dal.getMenuDAO().deleteMenu(menuDTO, sharedpreferences.getString(ConstantesComun.PACIENTE_KEY, ""));
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
