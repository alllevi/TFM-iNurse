package tfm.muuinf.viciano.lledo.alejandro.inurse.DTO;

import java.util.Date;

/**
 * Created by Alex on 18/02/2017.
 */

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

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setHorasRepeticion(Integer horasRepeticion) {
        this.horasRepeticion = horasRepeticion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
