package tfm.muuinf.viciano.lledo.alejandro.inurse.DTO;

/**
 * Created by Alex on 31/01/2017.
 */

public class UsuarioDTO {

    private int usuarioKey;
    private String usuario;
    private String password;
    private String tipo;
    private int pacienteKey;

    public UsuarioDTO() {

    }

    public UsuarioDTO(final int usuarioKey, final String usuario, final String password, final String tipo, final int pacienteKey) {
        this.usuarioKey = usuarioKey;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
        this.pacienteKey = pacienteKey;
    }

    public int getUsuarioKey() {
        return this.usuarioKey;
    }

    public void setUsuarioKey(final int usuarioKey) {
        this.usuarioKey = usuarioKey;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(final String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public int getPacienteKey() {
        return this.pacienteKey;
    }

    public void setPacienteKey(final int pacienteKey) {
        this.pacienteKey = pacienteKey;
    }
}
