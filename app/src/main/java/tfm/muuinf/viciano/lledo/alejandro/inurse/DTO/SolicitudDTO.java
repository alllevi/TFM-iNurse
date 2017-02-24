package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

import java.util.Date;

public class SolicitudDTO {

    private Integer key;
    private String descripcion;
    private String descripcionLarga;
    private Integer prioridad;
    private String prioridadDescripcion;
    private String tipoCodigo;
    private String tipoDescripcion;
    private Date fecha;
    private String paciente;
    private String habitacion;

    public SolicitudDTO() {

    }

    public SolicitudDTO(final Integer key, final String descripcion, final String descripcionLarga, final Integer prioridad, final String prioridadDescripcion, final String tipoCodigo, final String tipoDescripcion, final Date fecha) {
        this.key = key;
        this.descripcion = descripcion;
        this.descripcionLarga = descripcionLarga;
        this.prioridad = prioridad;
        this.prioridadDescripcion = prioridadDescripcion;
        this.tipoCodigo = tipoCodigo;
        this.tipoDescripcion = tipoDescripcion;
        this.fecha = fecha;
    }

    public SolicitudDTO(final Integer key, final String descripcion, final String descripcionLarga, final Integer prioridad, final String prioridadDescripcion, final String tipoCodigo, final String tipoDescripcion, final Date fecha, final String paciente, final String habitacion) {
        this.key = key;
        this.descripcion = descripcion;
        this.descripcionLarga = descripcionLarga;
        this.prioridad = prioridad;
        this.prioridadDescripcion = prioridadDescripcion;
        this.tipoCodigo = tipoCodigo;
        this.tipoDescripcion = tipoDescripcion;
        this.fecha = fecha;
        this.paciente = paciente;
        this.habitacion = habitacion;
    }

    public Integer getKey() {
        return key;
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

    public void setKey(final Integer key) {
        this.key = key;
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

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
}

