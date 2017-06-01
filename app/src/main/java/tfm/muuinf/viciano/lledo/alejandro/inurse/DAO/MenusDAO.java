package tfm.muuinf.viciano.lledo.alejandro.inurse.dao;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MenuDTO;

public class MenusDAO extends iNurseDAO {
    private static DateTime inicioDia;
    private static DateTime desayuno;
    private static DateTime comida;
    private static DateTime merienda;

    public MenusDAO() {
        initCalendar();
    }

    public List<MenuDTO> getMenus(String pacienteKey) throws Exception {
        String codigo = getCodigoNow();
        URL url = new URL(ConstantesDAO.GET_MENUS_BY_CODIGO + "codigo=" + codigo);
        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("menus");

        List<MenuDTO> listaMenuDTO = new ArrayList<>();
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            Integer menuKey = Integer.parseInt(jsonObjectUsuario.get("menu_key").toString());
            String menuCod = jsonObjectUsuario.get("menu_cod").toString();
            String descPrimero = jsonObjectUsuario.get("descPrimero").toString();
            String descSegundo = jsonObjectUsuario.get("descSegundo").toString();
            String descPostre = jsonObjectUsuario.get("descPostre").toString();

            listaMenuDTO.add(new MenuDTO(menuKey, menuCod, descPrimero, descSegundo, descPostre));
        }

        Integer menuPrecargaKey = precargaMenu(codigo, pacienteKey);
        if (menuPrecargaKey != null) {
            for (MenuDTO menu : listaMenuDTO) {
                if (menuPrecargaKey.equals(menu.getMenuKey())) {
                    menu.setPrecarga(true);
                }
            }
        }
        return listaMenuDTO;
    }

    public boolean insertarMenu(final MenuDTO menuDTO, String pacienteKey) throws IOException {
        String stringURL = ConstantesDAO.INSERT_UPDATE_MENU
                + "menuKey=" + menuDTO.getMenuKey()
                + "&codigo=" + menuDTO.getCodigo()
                + "&paciKey=" + pacienteKey;

        URL url = new URL(stringURL);
        String codigoRespuesta = insertHTTP(url);
        return "1".equals(codigoRespuesta);
    }

    public boolean deleteMenu(final MenuDTO menuDTO, String pacienteKey) throws IOException {
        String stringURL = ConstantesDAO.DELETE_MENU
                + "&codigo=" + menuDTO.getCodigo()
                + "&paciKey=" + pacienteKey;

        URL url = new URL(stringURL);
        String codigoRespuesta = insertHTTP(url);
        return "1".equals(codigoRespuesta);
    }

    public List<MenuDTO> getListMenus() throws Exception {
        String codigo = getCodigoNow();
        URL url = new URL(ConstantesDAO.GET_LIST_MENUS
                + "codigo=" + codigo);

        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("menus_solicitados");

        List<MenuDTO> listaMenuDTO = new ArrayList<>();
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            Integer mesoKey = Integer.parseInt(jsonObjectUsuario.get("mesoKey").toString());
            String primerPlato = jsonObjectUsuario.get("primerPlato").toString();
            String segundoPlato = jsonObjectUsuario.get("segundoPlato").toString();
            String postre = jsonObjectUsuario.get("postre").toString();
            String nombre = jsonObjectUsuario.get("nombre").toString();
            String primerApellido = jsonObjectUsuario.get("primerApellido").toString();
            String segundoApellido = jsonObjectUsuario.get("segundoApellido").toString();
            Integer planta = Integer.parseInt(jsonObjectUsuario.get("planta").toString());
            String habitacion = jsonObjectUsuario.get("habitacion").toString();
            String plantaHabitacion = planta.toString() + "-" + habitacion;
            String paciente = nombre + " " + primerApellido + " " + segundoApellido;

            MenuDTO menuDTO = new MenuDTO(mesoKey, codigo, primerPlato, segundoPlato, postre, planta, plantaHabitacion, paciente);
            listaMenuDTO.add(menuDTO);
        }
        return listaMenuDTO;
    }

    private Integer precargaMenu(String codigo, String pacienteKey) throws Exception {
        URL url = new URL(ConstantesDAO.PRECARGA_MENU
                + "&codigo=" + codigo
                + "&paciKey=" + pacienteKey);

        JSONObject jsonObject = getHTTP(url);
        JSONArray jsonArrayUsuarios = jsonObject.getJSONArray("menus_solicitados");
        Integer menuKey = null;
        for (int i = 0; i < jsonArrayUsuarios.length(); i++) {
            JSONObject jsonObjectUsuario = jsonArrayUsuarios.getJSONObject(i);
            menuKey = Integer.parseInt(jsonObjectUsuario.get("menu_key").toString());
        }
        return menuKey;
    }

    private String getCodigoNow() {
        DateTime dateNow = new DateTime();
        String codigo;
        if (inicioDia.isBefore(dateNow) && desayuno.isAfter(dateNow)) {
            codigo = "TIPMENU1";
        } else if (desayuno.isBefore(dateNow) && comida.isAfter(dateNow)) {
            codigo = "TIPMENU2";
        } else if (comida.isBefore(dateNow) && merienda.isAfter(dateNow)) {
            codigo = "TIPMENU3";
        } else {
            codigo = "TIPMENU4";
        }
        return codigo;
    }

    private void initCalendar() {
        inicioDia = new DateTime()
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0);

        desayuno = new DateTime()
                .withHourOfDay(12)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0);

        comida = new DateTime()
                .withHourOfDay(15)
                .withMinuteOfHour(30)
                .withSecondOfMinute(0);

        merienda = new DateTime()
                .withHourOfDay(18)
                .withMinuteOfHour(30)
                .withSecondOfMinute(0);
    }
}
