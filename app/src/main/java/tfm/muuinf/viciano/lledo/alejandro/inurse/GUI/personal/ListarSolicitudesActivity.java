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
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MaestroTiposDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.SolicitudDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores.AdapterListarSolicitudes;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class ListarSolicitudesActivity extends InurseActivity {

    public static final String TIPEST1 = "TIPEST1";
    public static final String TIPEST3 = "TIPEST3";
    public static final String TIPEST5 = "TIPEST5";
    public static final int REQUEST_CODE = 1;
    private Spinner cbEstados;
    private RecyclerView recyclerView;
    private List<SolicitudDTO> listaSolicitudes;
    private List<SolicitudDTO> listaSolicitudesEnUso;
    private List<MaestroTiposDTO> listaMaestroTipos;
    private Map<String, String> hashMapMaestroTipos;
    private String codigoSelected;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_solicitudes);
        initComponentes();
        initTask();
    }

    private void initComponentes() {
        codigoSelected = TIPEST1;
        hashMapMaestroTipos = new HashMap<>();
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
            //Creamos una map para los maestroTipos
            hashMapMaestroTipos.put(maestro.getCodigo(), maestro.getDescripcion());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        cbEstados.setAdapter(arrayAdapter);
        cbEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<SolicitudDTO> listaFiltrada = new ArrayList<>();
                codigoSelected = listaMaestroTipos.get(position).getCodigo();

                if (!TIPEST1.equals(codigoSelected)) {
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

    public void onClickRechazar(Integer solicitudKey) {
        Intent rechazarSolicitudIntent = new Intent(this, RechazarSolicitudActivity.class);
        rechazarSolicitudIntent.putExtra("solicitudKey", solicitudKey);
        startActivityForResult(rechazarSolicitudIntent, REQUEST_CODE);
    }

    public void onClickEnProgreso(Integer solicitudKey) {
        if (checkInternet()) {
            SolicitudEnProgresoTask solicitudEnProgresoTask = new SolicitudEnProgresoTask(solicitudKey);
            solicitudEnProgresoTask.execute((Void) null);
        }
    }

    private void updateLists(Integer solicitudKey, String codigo) {
        //Actualizamos la lista en uso
        SolicitudDTO solicitudBorrar = new SolicitudDTO();
        for (SolicitudDTO solicitud : listaSolicitudesEnUso) {
            if (solicitudKey.equals(solicitud.getKey())) {
                //Si tenemos seleccionada la pestaña todos actualizamos el valor, en caso contrario borramos el valor
                if (!TIPEST1.equals(codigoSelected)) {
                    solicitudBorrar = solicitud;
                } else {
                    solicitud.setTipoCodigo(codigo);
                    solicitud.setTipoDescripcion(hashMapMaestroTipos.get(codigo));
                }
            }
        }
        listaSolicitudesEnUso.remove(solicitudBorrar);

        //Actualizamos la lista completa
        for (SolicitudDTO solicitud : listaSolicitudes) {
            if (solicitudKey.equals(solicitud.getKey())) {
                solicitud.setTipoCodigo(codigo);
                solicitud.setTipoDescripcion(hashMapMaestroTipos.get(codigo));
            }
        }
        AdapterListarSolicitudes adapter = new AdapterListarSolicitudes(ListarSolicitudesActivity.this, listaSolicitudesEnUso, codigoSelected);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            // code to handle cancelled state
        } else if (requestCode == REQUEST_CODE) {
            String accion = data.getStringExtra("accion");
            Integer solicitudKey = data.getIntExtra("solicitudKey", 0);
            if ("borrar".equals(accion)) {
                updateLists(solicitudKey, TIPEST5);
            }
        }
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
                updateLists(solicitudKey, TIPEST3);
                Toast.makeText(getApplicationContext(), "Se ha actualizado el estado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
