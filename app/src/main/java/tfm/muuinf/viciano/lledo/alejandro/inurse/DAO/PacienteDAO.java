package tfm.muuinf.viciano.lledo.alejandro.inurse.DAO;

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

        final JSONObject jsonObject = realizarPeticionHTTP(url);
        final JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("usuarios");

        if (jsonArrayUsuarios.length() == 0) {
            return new UsuarioDTO();
        } else {

            final JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(0);
            final int usu_key = Integer.parseInt(jsonObjectUsuario.get("usu_key").toString());
            final String usu_usuario = jsonObjectUsuario.get("usu_usuario").toString();
            final String usu_password = jsonObjectUsuario.get("usu_password").toString();
            final String usu_tipo = jsonObjectUsuario.get("usu_tipo").toString();
            final int paci_key = Integer.parseInt(jsonObjectUsuario.get("paci_key").toString());
            return new UsuarioDTO(usu_key, usu_usuario, usu_password, usu_tipo, paci_key);
        }
    }

}
