package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

import android.widget.ImageView;

public class PacienteDTO {

    private Integer key;
    private ImageView imagenPaciente;
    private String habitacion;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String edad;
    private String sip;
    private String nhc;
    private String motivoIngreso;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String provincia;
    private String nacionalidad;

    public PacienteDTO(Integer key, ImageView imagenPaciente, String habitacion, String nombre, String apellidos, String sexo, String edad, String sip, String nhc, String motivoIngreso, String telefono, String direccion, String ciudad, String provincia, String nacionalidad) {
        this.key = key;
        this.imagenPaciente = imagenPaciente;
        this.habitacion = habitacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.edad = edad;
        this.sip = sip;
        this.nhc = nhc;
        this.motivoIngreso = motivoIngreso;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.nacionalidad = nacionalidad;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public ImageView getImagenPaciente() {
        return imagenPaciente;
    }

    public void setImagenPaciente(ImageView imagenPaciente) {
        this.imagenPaciente = imagenPaciente;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getNhc() {
        return nhc;
    }

    public void setNhc(String nhc) {
        this.nhc = nhc;
    }

    public String getMotivoIngreso() {
        return motivoIngreso;
    }

    public void setMotivoIngreso(String motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
