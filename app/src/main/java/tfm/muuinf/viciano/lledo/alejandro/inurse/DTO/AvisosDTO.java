package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

import java.util.Date;

public class AvisosDTO {
    private Integer key;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer horasRepeticion;
    private String descripcion;

    public AvisosDTO(Integer key, Date fechaInicio, Date fechaFin, Integer horasRepeticion, String descripcion) {
        this.key = key;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horasRepeticion = horasRepeticion;
        this.descripcion = descripcion;
    }

    public Integer getKey() {
        return key;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Integer getHorasRepeticion() {
        return horasRepeticion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
