package tfm.muuinf.viciano.lledo.alejandro.inurse.DAO;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.UsuarioDTO;

/**
 * Created by Alex on 31/01/2017.
 */

public class PacienteDAO extends BasicDAO {

    public PacienteDAO() {
    }

    public UsuarioDTO autenticarUsuario(final String usuario, final String password) throws Exception {

        final URL url = new URL(ConstantesDAO.SERVICIO_AUTENTICACION +
                "usuario=" + usuario + "&password=" + password);

        final JSONObject jsonObject = getHTTP(url);
        final JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("usuarios");

        if (jsonArrayUsuarios.length() == 0) {
            return null;
        } else {

            final JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(0);
            final Integer usuKey = Integer.parseInt(jsonObjectUsuario.get("usu_key").toString());
            final String usuUsuario = jsonObjectUsuario.get("usu_usuario").toString();
            final String usuPassword = jsonObjectUsuario.get("usu_password").toString();
            final String usuTipo = jsonObjectUsuario.get("usu_tipo").toString();

            Integer paciKey = null;
            final String stringPaciKey = jsonObjectUsuario.get("paci_key").toString();
            if (StringUtils.isNotBlank(stringPaciKey)) {
                paciKey = Integer.parseInt(stringPaciKey);
            }
            return new UsuarioDTO(usuKey, usuUsuario, usuPassword, usuTipo, paciKey);
        }
    }

}
