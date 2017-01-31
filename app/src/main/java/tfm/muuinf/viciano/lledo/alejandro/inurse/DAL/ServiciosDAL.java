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

        this.menusDAO = new MenusDAO();
        this.pacienteDAO = new PacienteDAO();
    }

    public MenusDAO getMenusDAO() {
        return this.menusDAO;
    }

    public PacienteDAO getPacienteDAO() {
        return this.pacienteDAO;
    }
}
