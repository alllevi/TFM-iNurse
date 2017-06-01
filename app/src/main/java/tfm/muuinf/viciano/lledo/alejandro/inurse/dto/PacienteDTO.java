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

    public ImageView getImagenPaciente() {
        return imagenPaciente;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEdad() {
        return edad;
    }

    public String getSip() {
        return sip;
    }

    public String getNhc() {
        return nhc;
    }

    public String getMotivoIngreso() {
        return motivoIngreso;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
}
