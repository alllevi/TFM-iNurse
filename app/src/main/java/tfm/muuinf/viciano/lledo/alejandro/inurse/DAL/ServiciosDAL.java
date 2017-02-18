package tfm.muuinf.viciano.lledo.alejandro.inurse.DAL;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DAO.MaestrosDAO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DAO.MenusDAO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DAO.PacienteDAO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DAO.SolicitudDAO;

/**
 * Created by Alex on 30/01/2017.
 */

public class ServiciosDAL {

    private final MenusDAO menusDAO;
    private final PacienteDAO pacienteDAO;
    private final SolicitudDAO solicitudDAO;
    private final MaestrosDAO maestrosDAO;

    public ServiciosDAL() {
        menusDAO = new MenusDAO();
        pacienteDAO = new PacienteDAO();
        solicitudDAO = new SolicitudDAO();
        maestrosDAO = new MaestrosDAO();
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
}
