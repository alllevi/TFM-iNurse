package tfm.muuinf.viciano.lledo.alejandro.inurse.DAL;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DAO.MenusDAO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.DAO.PacienteDAO;

/**
 * Created by Alex on 30/01/2017.
 */

public class ServiciosDAL {

    private final MenusDAO menusDAO;

    private final PacienteDAO pacienteDAO;

    public ServiciosDAL() {

        menusDAO = new MenusDAO();
        pacienteDAO = new PacienteDAO();
    }

    public MenusDAO getMenusDAO() {
        return menusDAO;
    }

    public PacienteDAO getPacienteDAO() {
        return pacienteDAO;
    }
}
