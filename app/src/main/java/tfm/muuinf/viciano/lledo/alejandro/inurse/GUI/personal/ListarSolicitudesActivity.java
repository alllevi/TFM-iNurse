package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MaestroTiposDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.SolicitudDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores.AdapterListarSolicitudes;

public class ListarSolicitudesActivity extends AppCompatActivity {

    private Spinner cbEstados;
    private RecyclerView recyclerView;
    private List<SolicitudDTO> listaSolicitudes;
    private List<SolicitudDTO> listaSolicitudesEnUso;
    private List<MaestroTiposDTO> listaMaestroTipos;
    private String codigoSelected;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_solicitudes);
        initComponentes();
        initTask();
    }

    private void initComponentes() {
        codigoSelected = "TIPEST1";
        cbEstados = (Spinner) findViewById(R.id.cb_filtro_listar_solicitudes);
        recyclerView = (RecyclerView) findViewById(R.id.rv_listar_solicitudes);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
    }

    private void rellenarRecyclerView(List<SolicitudDTO> lista, String estado) {
        AdapterListarSolicitudes adapter = new AdapterListarSolicitudes(ListarSolicitudesActivity.this, lista, estado);
        recyclerView.setAdapter(adapter);
    }

    private void rellenarComboEstados() {
        List<String> arrayList = new ArrayList<>();
        for (MaestroTiposDTO maestro : listaMaestroTipos) {
            arrayList.add(maestro.getDescripcion());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        cbEstados.setAdapter(arrayAdapter);
        cbEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<SolicitudDTO> listaFiltrada = new ArrayList<>();
                codigoSelected = listaMaestroTipos.get(position).getCodigo();

                if (!"TIPEST1".equals(codigoSelected)) {
                    for (SolicitudDTO solicitud : listaSolicitudes) {
                        String codigoSolicitud = solicitud.getTipoCodigo();
                        if (codigoSelected.equals(codigoSolicitud)) {
                            listaFiltrada.add(solicitud);
                        }
                    }
                    rellenarRecyclerView(listaFiltrada, codigoSelected);
                    listaSolicitudesEnUso = listaFiltrada;
                } else {
                    // Se selecciona mostrar todos y se muestra la lista original
                    rellenarRecyclerView(listaSolicitudes, codigoSelected);
                    listaSolicitudesEnUso = listaSolicitudes;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initTask() {
        if (checkInternet()) {
            ListarSolicitudesTask solicitudesTask = new ListarSolicitudesTask();
            solicitudesTask.execute((Void) null);
        }
    }

    private boolean checkInternet() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Compruebe su conexión a internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void onClickRechazar(Integer solicitudKey) {
        final Intent RechazarSolicitudIntent = new Intent(this, RechazarSolicitudActivity.class);
        startActivity(RechazarSolicitudIntent);
    }

    public void onClickEnProgreso(Integer solicitudKey) {
        if (checkInternet()) {
            SolicitudEnProgresoTask solicitudEnProgresoTask = new SolicitudEnProgresoTask(solicitudKey);
            solicitudEnProgresoTask.execute((Void) null);
        }
    }

    @Override
    public void onBackPressed() {
        // Boton atras
    }

    /**
     * Listar solicitudes task
     */
    private class ListarSolicitudesTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                listaSolicitudes = dal.getSolicitudDAO().getAllSolicitudes();
                listaMaestroTipos = dal.getMaestrosDAO().getMaestroTipos();
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                rellenarRecyclerView(listaSolicitudes, codigoSelected);
                rellenarComboEstados();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Solicitud en progreso task
     */
    private class SolicitudEnProgresoTask extends AsyncTask<Void, Void, Boolean> {

        private Integer solicitudKey;

        public SolicitudEnProgresoTask(Integer solicitudKey) {
            this.solicitudKey = solicitudKey;
        }

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            try {
                return dal.getSolicitudDAO().updateToProgreso(solicitudKey);
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                updateLists();
                Toast.makeText(getApplicationContext(), "Se ha actualizado el estado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }

        private void updateLists() {
            for (int i = 0; i < listaSolicitudesEnUso.size(); i++) {
                if (solicitudKey.equals(listaSolicitudesEnUso.get(i).getKey())) {
                    listaSolicitudesEnUso.remove(i);
                }
            }
            for (SolicitudDTO solicitud : listaSolicitudes) {
                if (solicitudKey.equals(solicitud.getKey())) {
                    solicitud.setTipoCodigo("TIPEST3");
                }
            }
            AdapterListarSolicitudes adapter = new AdapterListarSolicitudes(ListarSolicitudesActivity.this, listaSolicitudesEnUso, codigoSelected);
            recyclerView.setAdapter(adapter);
        }
    }
}
