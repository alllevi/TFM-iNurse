package tfm.muuinf.viciano.lledo.alejandro.inurse.dao;


import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MapaHospitalarioDTO;

public class MapaHospitalarioDAO extends iNurseDAO {

    public MapaHospitalarioDAO() {
    }

    public List<MapaHospitalarioDTO> getMapaHospitalario() throws Exception {
        URL url = new URL(ConstantesDAO.MAPA_HOSPITALARIO);

        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayMapaCamas = jsonObject.getJSONArray("mapa_hospitalario");

        List<MapaHospitalarioDTO> listaMapaHospitalarioDTO = new ArrayList<>();

        for (int i = 0; i < jsonArrayMapaCamas.length(); i++) {
            JSONObject jsonObjectMapaCama = jsonArrayMapaCamas.getJSONObject(i);
            Integer mapaKey = Integer.parseInt(jsonObjectMapaCama.get("mapa_key").toString());
            Integer mapaPlanta = Integer.parseInt(jsonObjectMapaCama.get("mapa_planta").toString());

            StringBuilder habitacionBuilder = new StringBuilder();
            habitacionBuilder.append(mapaPlanta.toString()).append("-");
            habitacionBuilder.append(jsonObjectMapaCama.get("mapa_habitacion").toString());

            StringBuilder paciBuilder = new StringBuilder();
            paciBuilder.append(jsonObjectMapaCama.get("paci_nombre").toString()).append(" ");
            paciBuilder.append(jsonObjectMapaCama.get("paci_primer_apellido").toString()).append(" ");
            paciBuilder.append(jsonObjectMapaCama.get("paci_segundo_apellido").toString());

            String paciSexo = jsonObjectMapaCama.get("paci_sexo").toString();
            String paciMotivoIngreso = jsonObjectMapaCama.get("paci_motivo_ingreso").toString();
            Integer paciKey = Integer.parseInt(jsonObjectMapaCama.get("paci_key").toString());

            listaMapaHospitalarioDTO.add(new MapaHospitalarioDTO(mapaKey, habitacionBuilder.toString(), mapaPlanta, paciBuilder.toString(), paciSexo, paciMotivoIngreso, paciKey));
        }
        return listaMapaHospitalarioDTO;
    }

    public Integer getMaxPlanta() throws Exception {

        URL url = new URL(ConstantesDAO.MAX_PLANTA);

        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayMaxPlanta = jsonObject.getJSONArray("mapa_hospitalario");
        JSONObject jsonObjectMaxPlanta = jsonArrayMaxPlanta.getJSONObject(0);

        return Integer.parseInt(jsonObjectMaxPlanta.get("max_planta").toString());
    }
}
