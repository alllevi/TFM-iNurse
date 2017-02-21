package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.pacientes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores.AdapterSeleccionMenus;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class SeleccionMenuActivity extends InurseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_menu);

        recyclerView = (RecyclerView) findViewById(R.id.rv_seleccion_menu);
        rellenarRecyclerView();
        new getMenus().execute(1, 1, 1);
    }

    private void rellenarRecyclerView() {
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        final AdapterSeleccionMenus adapter = new AdapterSeleccionMenus();
        recyclerView.setAdapter(adapter);
    }
    

    private class getMenus extends AsyncTask<Integer, Integer, Long> {

        @Override
        protected Long doInBackground(final Integer... params) {
            final ServiciosDAL serviciosDAL = new ServiciosDAL();
            serviciosDAL.getMenusDAO().getMenus();
            return null;
        }

        protected void onProgressUpdate(final Integer... progress) {
        }

        protected void onPostExecute(final Long result) {
        }
    }
}
