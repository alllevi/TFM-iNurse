package tfm.muuinf.viciano.lledo.alejandro.inurse.DTO;

/**
 * Created by Alex on 18/02/2017.
 */

public class MaestroTiposDTO {
    private Integer key;
    private String codigo;
    private String descripcion;

    public MaestroTiposDTO(Integer key, String codigo, String descripcion) {
        this.key = key;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
