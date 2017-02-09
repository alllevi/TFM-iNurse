package tfm.muuinf.viciano.lledo.alejandro.inurse.DAO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.SolicitudDTO;

/**
 * Created by Alex on 09/02/2017.
 */

public class SolicitudDAO extends BasicDAO {

    public SolicitudDAO() {
    }

    public List<SolicitudDTO> getSolicitudesByPacienteKey(final Integer pacienteKey) throws Exception {

        final URL url = new URL(ConstantesDAO.SOLICITUDES_BY_PACI_KEY + "paciKey=" + pacienteKey);

        final JSONObject jsonObject = realizarPeticionHTTP(url);
        final JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("solicitudes");

        final List<SolicitudDTO> listaSolicitudDTO = new ArrayList<>();

        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            final JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            final Integer soliKey = Integer.parseInt(jsonObjectUsuario.get("soli_key").toString());
            final String masoliDesc = jsonObjectUsuario.get("masoli_desc").toString();
            final String soliDesc = jsonObjectUsuario.get("soli_desc").toString();
            final Integer maprioPrioridad = Integer.parseInt(jsonObjectUsuario.get("maprio_prioridad").toString());
            final String maprioDesc = jsonObjectUsuario.get("maprio_desc").toString();
            final String matiCod = jsonObjectUsuario.get("mati_cod").toString();
            final String matiDesc = jsonObjectUsuario.get("mati_desc").toString();
            final Date soliFecha = formatter.parse(jsonObjectUsuario.get("soli_fecha").toString());
            listaSolicitudDTO.add(new SolicitudDTO(soliKey, masoliDesc, soliDesc, maprioPrioridad, maprioDesc, matiCod, matiDesc, soliFecha));
        }
        return listaSolicitudDTO;
    }
}
