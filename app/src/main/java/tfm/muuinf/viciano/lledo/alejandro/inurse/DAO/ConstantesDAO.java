package tfm.muuinf.viciano.lledo.alejandro.inurse.DAO;

/**
 * Created by Alex on 31/01/2017.
 */

public class ConstantesDAO {

    //public static final String URL_INURSE = "http://192.168.1.250/iNurse/";
    public static final String URL_INURSE = "http://192.168.1.37/iNurse/";

    public static String SERVICIO_AUTENTICACION = URL_INURSE + "autenticarUsuario.php?";

    public static String SOLICITUDES_BY_PACI_KEY = URL_INURSE + "getSolicitudesByPaciKey?";

    public static String MAESTRO_SOLICITUDES = URL_INURSE + "getMaestroSolicitudes";

    public static String INSERTAR_SOLICITUD = URL_INURSE + "insertarSolicitud?";

    public static String MAESTRO_PRIORIDADES = URL_INURSE + "getMaestroPrioridades";
}
