package tfm.muuinf.viciano.lledo.alejandro.inurse.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.SolicitudDTO;

public class SolicitudDAO extends BasicDAO {

    public SolicitudDAO() {
    }

    public List<SolicitudDTO> getSolicitudesByPacienteKey(final Integer pacienteKey) throws Exception {

        URL url = new URL(ConstantesDAO.SOLICITUDES_BY_PACI_KEY + "paciKey=" + pacienteKey);

        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("solicitudes");

        List<SolicitudDTO> listaSolicitudDTO = new ArrayList<>();

        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            Integer soliKey = Integer.parseInt(jsonObjectUsuario.get("soli_key").toString());
            String masoliDesc = jsonObjectUsuario.get("masoli_desc").toString();
            String soliDesc = jsonObjectUsuario.get("soli_desc").toString();
            Integer maprioPrioridad = Integer.parseInt(jsonObjectUsuario.get("maprio_prioridad").toString());
            String maprioDesc = jsonObjectUsuario.get("maprio_desc").toString();
            String matiCod = jsonObjectUsuario.get("mati_cod").toString();
            String matiDesc = jsonObjectUsuario.get("mati_desc").toString();
            Date soliFecha = formatter.parse(jsonObjectUsuario.get("soli_fecha").toString());
            listaSolicitudDTO.add(new SolicitudDTO(soliKey, masoliDesc, soliDesc, maprioPrioridad, maprioDesc, matiCod, matiDesc, soliFecha));
        }
        return listaSolicitudDTO;
    }

    public boolean insertarSolicitud(final String pacienteKey, final String maestroSolicitudKey, final String descripcion) throws IOException {

        String stringURL = ConstantesDAO.INSERTAR_SOLICITUD + "paciKey=" + pacienteKey +
                "&maSoliKey=" + maestroSolicitudKey +
                "&descripcion=" + descripcion;

        URL url = new URL(stringURL);
        String codigoRespuesta = insertHTTP(url);
        return "1".equals(codigoRespuesta);
    }

    public boolean updateToProgreso(final Integer key) throws IOException {

        String stringURL = ConstantesDAO.UPDATE_SOLICITUD_PROGRESO + "soliKey=" + key;

        URL url = new URL(stringURL);
        String codigoRespuesta = insertHTTP(url);
        return "1".equals(codigoRespuesta);
    }

    public boolean updateToRechazada(final Integer key, final String motivo) throws IOException {

        String stringURL = ConstantesDAO.UPDATE_SOLICITUD_RECHAZADA + "soliKey=" + key + "&motivo=" + motivo;

        URL url = new URL(stringURL);
        String codigoRespuesta = insertHTTP(url);
        return "1".equals(codigoRespuesta);
    }

    public List<SolicitudDTO> getAllSolicitudes() throws Exception {

        URL url = new URL(ConstantesDAO.SOLICITUDES_ALL);

        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("solicitudes");

        List<SolicitudDTO> listaSolicitudDTO = new ArrayList<>();

        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            Integer soliKey = Integer.parseInt(jsonObjectUsuario.get("soli_key").toString());
            String masoliDesc = jsonObjectUsuario.get("masoli_desc").toString();
            String soliDesc = jsonObjectUsuario.get("soli_desc").toString();
            Integer maprioPrioridad = Integer.parseInt(jsonObjectUsuario.get("maprio_prioridad").toString());
            String maprioDesc = jsonObjectUsuario.get("maprio_desc").toString();
            String matiCod = jsonObjectUsuario.get("mati_cod").toString();
            String matiDesc = jsonObjectUsuario.get("mati_desc").toString();
            Date soliFecha = formatter.parse(jsonObjectUsuario.get("soli_fecha").toString());
            listaSolicitudDTO.add(new SolicitudDTO(soliKey, masoliDesc, soliDesc, maprioPrioridad, maprioDesc, matiCod, matiDesc, soliFecha));
        }
        ordenarPorPrioridad(listaSolicitudDTO);
        return listaSolicitudDTO;
    }

    private void ordenarPorPrioridad(List<SolicitudDTO> lista) {
        Collections.sort(lista, new Comparator<SolicitudDTO>() {
            @Override
            public int compare(SolicitudDTO s1, SolicitudDTO s2) {
                return s1.getPrioridad().compareTo(s2.getPrioridad());
            }
        });
    }
}
