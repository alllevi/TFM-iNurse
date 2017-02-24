package tfm.muuinf.viciano.lledo.alejandro.inurse.dao;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.Date;

import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.PacienteDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.UsuarioDTO;

public class PacienteDAO extends iNurseDAO {

    public PacienteDAO() {
    }

    public UsuarioDTO autenticarUsuario(final String usuario, final String password) throws Exception {

        URL url = new URL(ConstantesDAO.SERVICIO_AUTENTICACION +
                "usuario=" + usuario + "&password=" + password);

        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("usuarios");

        if (jsonArrayUsuarios.length() == 0) {
            return null;
        } else {

            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(0);
            Integer usuKey = Integer.parseInt(jsonObjectUsuario.get("usu_key").toString());
            String usuUsuario = jsonObjectUsuario.get("usu_usuario").toString();
            String usuPassword = jsonObjectUsuario.get("usu_password").toString();
            String usuTipo = jsonObjectUsuario.get("usu_tipo").toString();

            Integer paciKey = null;
            String stringPaciKey = jsonObjectUsuario.get("paci_key").toString();
            if (StringUtils.isNotBlank(stringPaciKey)) {
                paciKey = Integer.parseInt(stringPaciKey);
            }
            return new UsuarioDTO(usuKey, usuUsuario, usuPassword, usuTipo, paciKey);
        }
    }

    public PacienteDTO getDetalle(Integer pacienteKey) throws Exception {

        URL url = new URL(ConstantesDAO.DETALLE_PACIENTE + "paciKey=" + pacienteKey);

        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("pacientes");
        JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(0);

        String habitacion = jsonObjectUsuario.get("mapa_planta").toString() + " - " + jsonObjectUsuario.get("mapa_habitacion").toString();
        String paciNombre = jsonObjectUsuario.get("paci_nombre").toString();
        String apellidos = jsonObjectUsuario.get("paci_primer_apellido").toString() + " " + jsonObjectUsuario.get("paci_segundo_apellido").toString();
        String paciSexo = jsonObjectUsuario.get("paci_sexo").toString();
        Date paciFechaNacimiento = formatter.parse(jsonObjectUsuario.get("paci_fecha_nacimiento").toString());
        String edad = String.valueOf(Years.yearsBetween(new LocalDate(paciFechaNacimiento), new LocalDate(new Date())).getYears());
        String paciSip = jsonObjectUsuario.get("paci_SIP").toString();
        String paciNhc = jsonObjectUsuario.get("paci_NHC").toString();
        String paciTelefono = jsonObjectUsuario.get("paci_telefono").toString();
        String paciDireccion = jsonObjectUsuario.get("paci_direccion").toString();
        String paciCiudad = jsonObjectUsuario.get("paci_ciudad").toString();
        String paciProvincia = jsonObjectUsuario.get("paci_provincia").toString();
        String paciNacionalidad = jsonObjectUsuario.get("paci_nacionalidad").toString();
        String paciMotivoIngreso = jsonObjectUsuario.get("paci_motivo_ingreso").toString();

        return new PacienteDTO(pacienteKey, null, habitacion, paciNombre, apellidos, paciSexo, edad, paciSip, paciNhc, paciMotivoIngreso, paciTelefono, paciDireccion, paciCiudad, paciProvincia, paciNacionalidad);
    }

}
