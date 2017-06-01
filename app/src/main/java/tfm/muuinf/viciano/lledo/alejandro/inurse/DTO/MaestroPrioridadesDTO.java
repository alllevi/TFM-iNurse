package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

public class MaestroPrioridadesDTO {

    private Integer key;
    private String descripcion;
    private Integer prioriodad;

    public MaestroPrioridadesDTO(Integer key, String descripcion, Integer prioriodad) {
        this.key = key;
        this.descripcion = descripcion;
        this.prioriodad = prioriodad;
    }

    public Integer getKey() {
        return key;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getPrioriodad() {
        return prioriodad;
    }

}
