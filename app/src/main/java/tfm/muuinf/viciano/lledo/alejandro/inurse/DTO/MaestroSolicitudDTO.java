package tfm.muuinf.viciano.lledo.alejandro.inurse.DTO;

/**
 * Created by Alex on 09/02/2017.
 */

public class MaestroSolicitudDTO {

    private Integer key;
    private String descripcion;
    private Integer prioridad;

    public MaestroSolicitudDTO() {
    }

    public MaestroSolicitudDTO(Integer key, String descripcion, Integer prioridad) {
        this.key = key;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }
}
