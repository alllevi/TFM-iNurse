package tfm.muuinf.viciano.lledo.alejandro.inurse.dto;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public class AvisoConfiguracionDTO {

    private Map<String, List<String>> mapHabitaciones;
    private Map<String, Pair<Integer, String>> mapPacientes;

    public AvisoConfiguracionDTO(Map<String, List<String>> mapHabitaciones, Map<String, Pair<Integer, String>> mapPacientes) {
        this.mapHabitaciones = mapHabitaciones;
        this.mapPacientes = mapPacientes;
    }

    public Map<String, List<String>> getMapHabitaciones() {
        return mapHabitaciones;
    }

    public Map<String, Pair<Integer, String>> getMapPacientes() {
        return mapPacientes;
    }
}
