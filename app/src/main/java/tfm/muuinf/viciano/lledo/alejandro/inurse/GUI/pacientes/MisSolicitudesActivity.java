package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.pacientes;

import android.content.SharedPreferences;
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
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MaestroTiposDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.SolicitudDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores.AdapterMisSolicitudes;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.ConstantesComun;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class MisSolicitudesActivity extends InurseActivity {

    private SharedPreferences sharedpreferences;
    private Spinner comboEstados;
    private RecyclerView recyclerView;
    private List<SolicitudDTO> listaSolicitudes;
    private List<MaestroTiposDTO> listaMaestroTipos;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_solicitudes);
        initComponents();
        initTask();
    }

    private void initComponents() {
        sharedpreferences = getSharedPreferences(ConstantesComun.SHARED_PREFS_FILE, ConstantesComun.CONTEXT_MODE_PRIVATE);
        comboEstados = (Spinner) findViewById(R.id.cb_filtro_mis_solicitudes);
        recyclerView = (RecyclerView) findViewById(R.id.rv_mis_solicitudes);
    }

    private void rellenarRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        AdapterMisSolicitudes adapter = new AdapterMisSolicitudes(listaSolicitudes);
        recyclerView.setAdapter(adapter);
    }

    private void rellenarComboEstados() {
        List<String> arrayList = new ArrayList<>();
        for (MaestroTiposDTO maestro : listaMaestroTipos) {
            arrayList.add(maestro.getDescripcion());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        comboEstados.setAdapter(arrayAdapter);
        comboEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<SolicitudDTO> listaFiltrada = new ArrayList<>();
                String codigoSelected = listaMaestroTipos.get(position).getCodigo();

                if (!"TIPEST1".equals(codigoSelected)) {
                    for (SolicitudDTO solicitud : listaSolicitudes) {
                        String codigoSolicitud = solicitud.getTipoCodigo();
                        if (codigoSelected.equals(codigoSolicitud)) {
                            listaFiltrada.add(solicitud);
                        }
                    }
                    AdapterMisSolicitudes adapter = new AdapterMisSolicitudes(listaFiltrada);
                    recyclerView.setAdapter(adapter);
                } else {
                    // Se selecciona mostrar todos y se muestra la lista original
                    AdapterMisSolicitudes adapter = new AdapterMisSolicitudes(listaSolicitudes);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void initTask() {
        if (checkInternet()) {
            MisSolicitudesTask solicitudesTask = new MisSolicitudesTask();
            solicitudesTask.execute((Void) null);
        }
    }

    private class MisSolicitudesTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... params) {
            ServiciosDAL dal = new ServiciosDAL();
            String pacienteKey = sharedpreferences.getString(ConstantesComun.PACIENTE_KEY, "");
            try {
                listaSolicitudes = dal.getSolicitudDAO().getSolicitudesByPacienteKey(Integer.parseInt(pacienteKey));
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
                rellenarRecyclerView();
                rellenarComboEstados();
            } else {
                Toast.makeText(getApplicationContext(), "Se ha producido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
