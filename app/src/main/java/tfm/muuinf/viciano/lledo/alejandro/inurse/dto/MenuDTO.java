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

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public Integer getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(Integer menuKey) {
        this.menuKey = menuKey;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrimero() {
        return primero;
    }

    public void setPrimero(String primero) {
        this.primero = primero;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

    public String getPostre() {
        return postre;
    }

    public void setPostre(String postre) {
        this.postre = postre;
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

    public void setPlanta(Integer planta) {
        this.planta = planta;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
}
