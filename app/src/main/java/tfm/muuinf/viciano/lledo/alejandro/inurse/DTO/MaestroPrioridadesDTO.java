package tfm.muuinf.viciano.lledo.alejandro.inurse.DTO;

/**
 * Created by Alex on 09/02/2017.
 */

public class MaestroPrioridadesDTO {

    private Integer prioridadKey;
    private String descripcion;
    private Integer prioriodad;

    public MaestroPrioridadesDTO() {
    }

    public MaestroPrioridadesDTO(Integer prioridadKey, String descripcion, Integer prioriodad) {
        this.prioridadKey = prioridadKey;
        this.descripcion = descripcion;
        this.prioriodad = prioriodad;
    }

    public Integer getPrioridadKey() {
        return prioridadKey;
    }

    public void setPrioridadKey(Integer prioridadKey) {
        this.prioridadKey = prioridadKey;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrioriodad() {
        return prioriodad;
    }

    public void setPrioriodad(Integer prioriodad) {
        this.prioriodad = prioriodad;
    }
}
