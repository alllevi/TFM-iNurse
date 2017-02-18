package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

public class MaestroPrioridadesDTO {

    private Integer key;
    private String descripcion;
    private Integer prioriodad;

    public MaestroPrioridadesDTO() {
    }

    public MaestroPrioridadesDTO(Integer key, String descripcion, Integer prioriodad) {
        this.key = key;
        this.descripcion = descripcion;
        this.prioriodad = prioriodad;
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

    public Integer getPrioriodad() {
        return prioriodad;
    }

    public void setPrioriodad(Integer prioriodad) {
        this.prioriodad = prioriodad;
    }
}
