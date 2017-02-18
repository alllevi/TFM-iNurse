package tfm.muuinf.viciano.lledo.alejandro.inurse.DAO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.MaestroPrioridadesDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.MaestroSolicitudDTO;

/**
 * Created by Alex on 09/02/2017.
 */

public class MaestrosDAO extends BasicDAO {

    public MaestrosDAO() {
    }

    public List<MaestroSolicitudDTO> getMaestroSolicitudes() throws Exception {
        final URL url = new URL(ConstantesDAO.MAESTRO_SOLICITUDES);
        final JSONObject jsonObject = getHTTP(url);
        final JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("maestro_solicitudes");

        final List<MaestroSolicitudDTO> listaMaestroSolicitudesDTO = new ArrayList<>();
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            final JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            final Integer masoliKey = Integer.parseInt(jsonObjectUsuario.get("masoli_key").toString());
            final String masoliDesc = jsonObjectUsuario.get("masoli_desc").toString();
            final Integer maprioKey = Integer.parseInt(jsonObjectUsuario.get("maprio_key").toString());
            listaMaestroSolicitudesDTO.add(new MaestroSolicitudDTO(masoliKey, masoliDesc, maprioKey));
        }
        return listaMaestroSolicitudesDTO;
    }

    public List<MaestroPrioridadesDTO> getMaestroPrioridades() throws Exception {
        final URL url = new URL(ConstantesDAO.MAESTRO_PRIORIDADES);
        final JSONObject jsonObject = getHTTP(url);
        final JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("maestro_prioridades");

        final List<MaestroPrioridadesDTO> listaMaestroPrioridadesDTO = new ArrayList<>();
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            final JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            final Integer maprioKey = Integer.parseInt(jsonObjectUsuario.get("maprio_key").toString());
            final String maprioDesc = jsonObjectUsuario.get("maprio_desc").toString();
            final Integer maprioPrioridad = Integer.parseInt(jsonObjectUsuario.get("maprio_prioridad").toString());
            listaMaestroPrioridadesDTO.add(new MaestroPrioridadesDTO(maprioKey, maprioDesc, maprioPrioridad));
        }
        return listaMaestroPrioridadesDTO;
    }
}
