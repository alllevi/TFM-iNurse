package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

import java.util.List;
import java.util.Map;

public class AvisoConfiguracionDTO {

    private Map<String, List<String>> mapHabitaciones;
    private Map<String, String> mapPacientes;

    public AvisoConfiguracionDTO(Map<String, List<String>> mapHabitaciones, Map<String, String> mapPacientes) {
        this.mapHabitaciones = mapHabitaciones;
        this.mapPacientes = mapPacientes;
    }

    public Map<String, List<String>> getMapHabitaciones() {
        return mapHabitaciones;
    }

    public void setMapHabitaciones(Map<String, List<String>> mapHabitaciones) {
        this.mapHabitaciones = mapHabitaciones;
    }

    public Map<String, String> getMapPacientes() {
        return mapPacientes;
    }

    public void setMapPacientes(Map<String, String> mapPacientes) {
        this.mapPacientes = mapPacientes;
    }
}
