package tfm.muuinf.viciano.lledo.alejandro.inurse.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.AvisosDTO;

public class AvisosDAO extends BasicDAO {

    public AvisosDAO() {
    }

    public List<AvisosDTO> getAvisosPaciente(String pacienteKey) throws Exception {
        URL url = new URL(ConstantesDAO.AVISOS_BY_PACI_KEY + "paciKey=" + pacienteKey);
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
