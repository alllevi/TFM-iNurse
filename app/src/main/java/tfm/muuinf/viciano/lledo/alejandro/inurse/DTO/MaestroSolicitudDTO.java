package tfm.muuinf.viciano.lledo.alejandro.inurse.DTO;

/**
 * Created by Alex on 09/02/2017.
 */

public class MaestroSolicitudDTO {

    private Integer maestroSolicitudKey;
    private String descripcion;
    private Integer prioridad;

    public MaestroSolicitudDTO() {
    }

    public MaestroSolicitudDTO(Integer maestroSolicitudKey, String descripcion, Integer prioridad) {
        this.maestroSolicitudKey = maestroSolicitudKey;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public Integer getMaestroSolicitudKey() {
        return maestroSolicitudKey;
    }

    public void setMaestroSolicitudKey(Integer maestroSolicitudKey) {
        this.maestroSolicitudKey = maestroSolicitudKey;
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
