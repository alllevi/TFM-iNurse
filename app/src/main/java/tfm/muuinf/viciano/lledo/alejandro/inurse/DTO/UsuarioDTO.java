package tfm.muuinf.viciano.lledo.alejandro.inurse.DTO;

/**
 * Created by Alex on 31/01/2017.
 */

public class UsuarioDTO {

    private Integer key;
    private String usuario;
    private String password;
    private String tipo;
    private Integer pacienteKey;

    public UsuarioDTO() {

    }

    public UsuarioDTO(final Integer key, final String usuario, final String password, final String tipo, final Integer pacienteKey) {
        this.key = key;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
        this.pacienteKey = pacienteKey;
    }

    public Integer getKey() {
        return this.key;
    }

    public void setKey(final int key) {
        this.key = key;
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

    public Integer getPacienteKey() {
        return this.pacienteKey;
    }

    public void setPacienteKey(final int pacienteKey) {
        this.pacienteKey = pacienteKey;
    }
}
