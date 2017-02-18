package tfm.muuinf.viciano.lledo.alejandro.inurse.DAO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.MaestroPrioridadesDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.MaestroSolicitudDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.MaestroTiposDTO;

/**
 * Created by Alex on 09/02/2017.
 */

public class MaestrosDAO extends BasicDAO {

    public MaestrosDAO() {
    }

    public List<MaestroSolicitudDTO> getMaestroSolicitudes() throws Exception {
        URL url = new URL(ConstantesDAO.MAESTRO_SOLICITUDES);
        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("maestro_solicitudes");

        List<MaestroSolicitudDTO> listaMaestroSolicitudesDTO = new ArrayList<>();
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            Integer masoliKey = Integer.parseInt(jsonObjectUsuario.get("masoli_key").toString());
            String masoliDesc = jsonObjectUsuario.get("masoli_desc").toString();
            Integer maprioKey = Integer.parseInt(jsonObjectUsuario.get("maprio_key").toString());
            listaMaestroSolicitudesDTO.add(new MaestroSolicitudDTO(masoliKey, masoliDesc, maprioKey));
        }
        return listaMaestroSolicitudesDTO;
    }

    public List<MaestroPrioridadesDTO> getMaestroPrioridades() throws Exception {
        URL url = new URL(ConstantesDAO.MAESTRO_PRIORIDADES);
        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("maestro_prioridades");

        List<MaestroPrioridadesDTO> listaMaestroPrioridadesDTO = new ArrayList<>();
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            Integer maprioKey = Integer.parseInt(jsonObjectUsuario.get("maprio_key").toString());
            String maprioDesc = jsonObjectUsuario.get("maprio_desc").toString();
            Integer maprioPrioridad = Integer.parseInt(jsonObjectUsuario.get("maprio_prioridad").toString());
            listaMaestroPrioridadesDTO.add(new MaestroPrioridadesDTO(maprioKey, maprioDesc, maprioPrioridad));
        }
        return listaMaestroPrioridadesDTO;
    }

    public List<MaestroTiposDTO> getMaestroTipos() throws Exception {
        URL url = new URL(ConstantesDAO.MAESTRO_TIPOS);
        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("maestro_tipos");

        List<MaestroTiposDTO> listaMaestroTipos = new ArrayList<>();
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            Integer matiKey = Integer.parseInt(jsonObjectUsuario.get("mati_key").toString());
            String matiCod = jsonObjectUsuario.get("mati_cod").toString();
            String matiDesc = jsonObjectUsuario.get("mati_desc").toString();
            listaMaestroTipos.add(new MaestroTiposDTO(matiKey, matiCod, matiDesc));
        }
        return listaMaestroTipos;
    }
}
