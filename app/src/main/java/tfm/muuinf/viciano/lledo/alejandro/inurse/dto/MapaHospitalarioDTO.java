package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

public class MapaHospitalarioDTO {

    private Integer key;
    private Integer planta;
    private String habitacion;
    private String paciente;
    private String sexo;
    private String motivo;
    private Integer pacienteKey;

    public MapaHospitalarioDTO(Integer key, String habitacion, Integer planta, String paciente, String sexo, String motivo, Integer pacienteKey) {
        this.key = key;
        this.planta = planta;
        this.habitacion = habitacion;
        this.paciente = paciente;
        this.sexo = sexo;
        this.motivo = motivo;
        this.pacienteKey = pacienteKey;
    }

    public Integer getKey() {
        return key;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getSexo() {
        return sexo;
    }

    public String getMotivo() {
        return motivo;
    }

    public Integer getPlanta() {
        return planta;
    }

    public Integer getPacienteKey() {
        return pacienteKey;
    }
}
