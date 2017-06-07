package tfm.muuinf.viciano.lledo.alejandro.inurse.dao;

import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.AvisoConfiguracionDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.AvisosDTO;

public class AvisosDAO extends iNurseDAO {

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
            Date avisoFechaInicio = formatterLong.parse(jsonObjectUsuario.get("aviso_fecha_inicio").toString());
            Date avisoFechaFin = formatterLong.parse(jsonObjectUsuario.get("aviso_fecha_fin").toString());
            Integer avisoHorasRepeticion = Integer.parseInt(jsonObjectUsuario.get("aviso_horas_repeticion").toString());
            String avisoDescripcion = jsonObjectUsuario.get("aviso_descripcion").toString();

            listaAvisosDTO.add(new AvisosDTO(avisoKey, avisoFechaInicio, avisoFechaFin, avisoHorasRepeticion, avisoDescripcion));
        }
        return listaAvisosDTO;
    }

    public List<AvisosDTO> getAvisosActivos() throws Exception {
        URL url = new URL(ConstantesDAO.GET_LIST_AVISOS);
        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("avisos");

        List<AvisosDTO> listaAvisosDTO = new ArrayList<>();
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            Integer avisoKey = Integer.parseInt(jsonObjectUsuario.get("aviso_key").toString());
            Date avisoFechaInicio = formatterLong.parse(jsonObjectUsuario.get("aviso_fecha_inicio").toString());
            Date avisoFechaFin = formatterLong.parse(jsonObjectUsuario.get("aviso_fecha_fin").toString());
            Integer avisoHorasRepeticion = Integer.parseInt(jsonObjectUsuario.get("aviso_horas_repeticion").toString());
            String avisoDescripcion = jsonObjectUsuario.get("aviso_descripcion").toString();
            String nombre = jsonObjectUsuario.get("nombre").toString();
            String primerApellido = jsonObjectUsuario.get("primerApellido").toString();
            String segundoApellido = jsonObjectUsuario.get("segundoApellido").toString();
            String planta = jsonObjectUsuario.get("planta").toString();
            String habitacion = jsonObjectUsuario.get("habitacion").toString();

            String paciente = nombre + " " + primerApellido + " " + segundoApellido;
            String habitacionPlanta = planta + "-" + habitacion;

            listaAvisosDTO.add(new AvisosDTO(avisoKey, avisoFechaInicio, avisoFechaFin, avisoHorasRepeticion, avisoDescripcion, paciente, Integer.parseInt(planta), habitacionPlanta));
        }
        return listaAvisosDTO;
    }

    public AvisoConfiguracionDTO getAvisosConfiguracion() throws Exception {
        URL url = new URL(ConstantesDAO.AVISOS_CONFIGURACION);
        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("mapa_hospitalario");

        Map<String, List<String>> mapHabitaciones = new HashMap<>();
        Map<String, Pair<Integer, String>> mapPacientes = new HashMap<>();
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            String mapaPlanta = jsonObjectUsuario.get("mapa_planta").toString();
            String mapaHabitacion = jsonObjectUsuario.get("mapa_habitacion").toString();
            Integer paciKey = Integer.parseInt(jsonObjectUsuario.get("paci_key").toString());
            String paciNombre = jsonObjectUsuario.get("paci_nombre").toString();
            String paciPrimerApellido = jsonObjectUsuario.get("paci_primer_apellido").toString();
            String paciSegundoApellido = jsonObjectUsuario.get("paci_segundo_apellido").toString();

            if (mapHabitaciones.containsKey(mapaPlanta)) {
                mapHabitaciones.get(mapaPlanta).add(mapaHabitacion);
            } else {
                List<String> listaHabitaciones = new ArrayList<>();
                listaHabitaciones.add(mapaHabitacion);
                mapHabitaciones.put(mapaPlanta, listaHabitaciones);
            }
            Pair<Integer, String> pairPaciente = Pair.of(paciKey, paciNombre + " " + paciPrimerApellido + " " + paciSegundoApellido);
            mapPacientes.put(mapaPlanta + mapaHabitacion, pairPaciente);
        }
        return new AvisoConfiguracionDTO(mapHabitaciones, mapPacientes);
    }

    public boolean insertarAviso(String fechaIni, String horaIni, String fechaFin, String repetirHoras, String descripcion, Integer pacienteKey) throws Exception {

        fechaIni = getStringDate(fechaIni);
        fechaFin = getStringDate(fechaFin);
        String dateTimeInicio = fechaIni + " " + horaIni;
        String dateTimeFin = fechaFin + " 00:00";

        String stringURL = ConstantesDAO.INSERTAR_AVISOS + "fechaInicio=" + dateTimeInicio +
                "&fechaFin=" + dateTimeFin +
                "&horasRepeticion=" + repetirHoras +
                "&descripcion=" + descripcion +
                "&paciKey=" + pacienteKey;

        URL url = new URL(stringURL);
        String codigoRespuesta = insertHTTP(url);
        return "1".equals(codigoRespuesta);
    }

    private String getStringDate(String fecha) {
        String[] split = fecha.split("/");
        String anyo = split[2];
        String mes = split[1];
        String dia = split[0];
        if (mes.length() == 1) {
            mes = "0" + mes;
        }
        if (dia.length() == 1) {
            dia = "0" + mes;
        }
        fecha = anyo + "/" + mes + "/" + dia;
        return fecha;
    }
}
