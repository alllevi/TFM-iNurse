package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

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

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
