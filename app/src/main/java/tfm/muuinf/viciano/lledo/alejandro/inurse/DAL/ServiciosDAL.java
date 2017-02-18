package tfm.muuinf.viciano.lledo.alejandro.inurse.dal;

import tfm.muuinf.viciano.lledo.alejandro.inurse.dao.AvisosDAO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dao.MaestrosDAO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dao.MenusDAO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dao.PacienteDAO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dao.SolicitudDAO;

public class ServiciosDAL {

    private final MenusDAO menusDAO;
    private final PacienteDAO pacienteDAO;
    private final SolicitudDAO solicitudDAO;
    private final MaestrosDAO maestrosDAO;
    private final AvisosDAO avisosDAO;

    public ServiciosDAL() {
        menusDAO = new MenusDAO();
        pacienteDAO = new PacienteDAO();
        solicitudDAO = new SolicitudDAO();
        maestrosDAO = new MaestrosDAO();
        avisosDAO = new AvisosDAO();
    }

    public MenusDAO getMenusDAO() {
        return menusDAO;
    }

    public MenusDAO getMenuDAO() {
        return menusDAO;
    }

    public PacienteDAO getPacienteDAO() {
        return pacienteDAO;
    }

    public SolicitudDAO getSolicitudDAO() {
        return solicitudDAO;
    }

    public MaestrosDAO getMaestrosDAO() {
        return maestrosDAO;
    }

    public AvisosDAO getAvisosDAO() {
        return avisosDAO;
    }
}
