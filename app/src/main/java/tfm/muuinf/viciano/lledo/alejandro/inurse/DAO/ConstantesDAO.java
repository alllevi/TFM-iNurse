package tfm.muuinf.viciano.lledo.alejandro.inurse.dao;

class ConstantesDAO {

    public static final String URL_INURSE = "http://192.168.1.249/iNurse/";
    //private static final String URL_INURSE = "http://192.168.1.37/iNurse/";

    public static String SERVICIO_AUTENTICACION = URL_INURSE + "autenticarUsuario?";

    public static String SOLICITUDES_BY_PACI_KEY = URL_INURSE + "getSolicitudesByPaciKey?";

    public static String SOLICITUDES_ALL = URL_INURSE + "getAllSolicitudes";

    public static String MAESTRO_SOLICITUDES = URL_INURSE + "getMaestroSolicitudes";

    public static String UPDATE_SOLICITUD_PROGRESO = URL_INURSE + "updateSolicitudToProgreso?";

    public static String UPDATE_SOLICITUD_RECHAZADA = URL_INURSE + "updateSolicitudToRechazada?";

    public static String INSERTAR_SOLICITUD = URL_INURSE + "insertarSolicitud?";

    public static String MAESTRO_PRIORIDADES = URL_INURSE + "getMaestroPrioridades";

    public static String MAESTRO_TIPOS = URL_INURSE + "getMaestroTipos";

    public static String AVISOS_BY_PACI_KEY = URL_INURSE + "getAvisosByPaciKey?";
}
