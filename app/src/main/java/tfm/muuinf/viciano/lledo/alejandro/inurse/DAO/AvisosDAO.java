package tfm.muuinf.viciano.lledo.alejandro.inurse.DAO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.AvisosDTO;

/**
 * Created by Alex on 18/02/2017.
 */

public class AvisosDAO extends BasicDAO {

    public AvisosDAO() {
    }

    public List<AvisosDTO> getAvisosPaciente(String pacienteKey) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(ConstantesDAO.AVISOS_BY_PACI_KEY);
        stringBuilder.append("paciKey=" + pacienteKey);
        URL url = new URL(stringBuilder.toString());
        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("avisos");

        List<AvisosDTO> listaAvisosDTO = new ArrayList<>();
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            Integer avisoKey = Integer.parseInt(jsonObjectUsuario.get("aviso_key").toString());
            Date avisoFechaInicio = formatter.parse(jsonObjectUsuario.get("aviso_fecha_inicio").toString());
            Date avisoFechaFin = formatter.parse(jsonObjectUsuario.get("aviso_fecha_fin").toString());
            Integer avisoHorasRepeticion = Integer.parseInt(jsonObjectUsuario.get("aviso_horas_repeticion").toString());
            String avisoDescripcion = jsonObjectUsuario.get("aviso_descripcion").toString();

            listaAvisosDTO.add(new AvisosDTO(avisoKey, avisoFechaInicio, avisoFechaFin, avisoHorasRepeticion, avisoDescripcion));
        }
        return listaAvisosDTO;
    }
}
