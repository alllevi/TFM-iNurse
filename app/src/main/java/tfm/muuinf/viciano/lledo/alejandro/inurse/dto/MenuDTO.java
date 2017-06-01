package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;


public class MenuDTO {

    private Integer menuKey;
    private String codigo;
    private String primero;
    private String segundo;
    private String postre;
    private Integer planta;
    private String habitacion;
    private String nombrePaciente;
    private boolean precarga;

    public MenuDTO(Integer menuKey, String codigo, String primero, String segundo, String postre) {
        this.menuKey = menuKey;
        this.codigo = codigo;
        this.primero = primero;
        this.segundo = segundo;
        this.postre = postre;
        this.precarga = false;
    }

    public MenuDTO(Integer menuKey, String codigo, String primero, String segundo, String postre, Integer planta, String habitacion, String nombrePaciente) {
        this.menuKey = menuKey;
        this.codigo = codigo;
        this.primero = primero;
        this.segundo = segundo;
        this.postre = postre;
        this.planta = planta;
        this.habitacion = habitacion;
        this.nombrePaciente = nombrePaciente;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public Integer getMenuKey() {
        return menuKey;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPrimero() {
        return primero;
    }

    public String getSegundo() {
        return segundo;
    }

    public String getPostre() {
        return postre;
    }

    public boolean isPrecarga() {
        return precarga;
    }

    public void setPrecarga(boolean precarga) {
        this.precarga = precarga;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public Integer getPlanta() {
        return planta;
    }
}
