package tfm.muuinf.viciano.lledo.alejandro.inurse.DTO;

import java.util.Date;

/**
 * Created by Alex on 09/02/2017.
 */

public class SolicitudDTO {

    private Integer solicitudKey;
    private String descripcion;
    private String descripcionLarga;
    private Integer prioridad;
    private String prioridadDescripcion;
    private String tipoCodigo;
    private String tipoDescripcion;
    private Date fecha;

    public SolicitudDTO() {

    }

    public SolicitudDTO(final Integer solicitudKey, final String descripcion, final String descripcionLarga, final Integer prioridad, final String prioridadDescripcion, final String tipoCodigo, final String tipoDescripcion, final Date fecha) {
        this.solicitudKey = solicitudKey;
        this.descripcion = descripcion;
        this.descripcionLarga = descripcionLarga;
        this.prioridad = prioridad;
        this.prioridadDescripcion = prioridadDescripcion;
        this.tipoCodigo = tipoCodigo;
        this.tipoDescripcion = tipoDescripcion;
        this.fecha = fecha;
    }

    public Integer getSolicitudKey() {
        return solicitudKey;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public String getPrioridadDescripcion() {
        return prioridadDescripcion;
    }

    public String getTipoCodigo() {
        return tipoCodigo;
    }

    public String getTipoDescripcion() {
        return tipoDescripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setSolicitudKey(final Integer solicitudKey) {
        this.solicitudKey = solicitudKey;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDescripcionLarga(final String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public void setPrioridad(final Integer prioridad) {
        this.prioridad = prioridad;
    }

    public void setPrioridadDescripcion(final String prioridadDescripcion) {
        this.prioridadDescripcion = prioridadDescripcion;
    }

    public void setTipoCodigo(final String tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    public void setTipoDescripcion(final String tipoDescripcion) {
        this.tipoDescripcion = tipoDescripcion;
    }

    public void setFecha(final Date fecha) {
        this.fecha = fecha;
    }
}

