package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

public class MaestroSolicitudDTO {

    private Integer key;
    private String descripcion;
    private Integer prioridad;

    public MaestroSolicitudDTO(Integer key, String descripcion, Integer prioridad) {
        this.key = key;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public Integer getKey() {
        return key;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

}
