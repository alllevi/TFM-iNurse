package tfm.muuinf.viciano.lledo.alejandro.inurse.dao;

class ConstantesDAO {

    private static final String URL_INURSE = "http://192.168.1.249/iNurse/";
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

    public static String MAPA_HOSPITALARIO = URL_INURSE + "getMapaHospitalario";

    public static String MAX_PLANTA = URL_INURSE + "getMaxPlanta";

    public static String DETALLE_PACIENTE = URL_INURSE + "getDetallePaciente?";

    public static String AVISOS_CONFIGURACION = URL_INURSE + "getAvisosConfiguracion";

    public static String INSERTAR_AVISOS = URL_INURSE + "insertarAviso?";

    public static String GET_MENUS_BY_CODIGO = URL_INURSE + "getMenusByCodigo?";

    public static String INSERT_UPDATE_MENU = URL_INURSE + "insertOrUpdateMenu?";

    public static String DELETE_MENU = URL_INURSE + "deleteMenu?";

    public static String PRECARGA_MENU = URL_INURSE + "precargaMenu?";

    public static String GET_LIST_MENUS = URL_INURSE + "getListMenus?";

}
