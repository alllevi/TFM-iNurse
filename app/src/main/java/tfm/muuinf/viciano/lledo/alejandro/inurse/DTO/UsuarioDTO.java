package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

public class UsuarioDTO {

    private Integer key;
    private String usuario;
    private String password;
    private String tipo;
    private Integer pacienteKey;

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

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getTipo() {
        return this.tipo;
    }

    public Integer getPacienteKey() {
        return this.pacienteKey;
    }
}
