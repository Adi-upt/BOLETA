package Controlador;

import Modelo.clsCargo;
import ModeloDAO.clsCargoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controlador del CRUD de Cargo.
 * Acciones: listar, add, Agregar, editar, Actualizar, eliminar
 * @author Hpuser
 */
public class ControladorCargo extends HttpServlet {

    private final String listar = "VistaCargo/listar.jsp";
    private final String add    = "VistaCargo/add.jsp";
    private final String edit   = "VistaCargo/edit.jsp";

    private final clsCargoDAO dao = new clsCargoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("accion");
        if (action == null || action.trim().isEmpty()) {
            action = "listar";
        }

        String acceso;

        if (action.equalsIgnoreCase("listar")) {
            request.setAttribute("cargos", dao.listar());
            request.setAttribute("error", dao.getError());
            acceso = listar;

        } else if (action.equalsIgnoreCase("add")) {
            acceso = add;

        } else if (action.equalsIgnoreCase("Agregar")) {
            clsCargo cargo = new clsCargo();
            cargo.setIdCargo(Integer.parseInt(request.getParameter("txtIdcargo")));
            cargo.setNombre(request.getParameter("txtNombre"));
            cargo.setDescripcion(request.getParameter("txtDescripcion"));
            dao.add(cargo);
            response.sendRedirect("ControladorCargo?accion=listar");
            return;

        } else if (action.equalsIgnoreCase("editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("cargo", dao.list(id));
            acceso = edit;

        } else if (action.equalsIgnoreCase("Actualizar")) {
            clsCargo cargo = new clsCargo();
            cargo.setIdCargo(Integer.parseInt(request.getParameter("txtId")));
            cargo.setNombre(request.getParameter("txtNombre"));
            cargo.setDescripcion(request.getParameter("txtDescripcion"));
            dao.edit(cargo);
            response.sendRedirect("ControladorCargo?accion=listar");
            return;

        } else if (action.equalsIgnoreCase("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect("ControladorCargo?accion=listar");
            return;

        } else {
            request.setAttribute("cargos", dao.listar());
            acceso = listar;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador CRUD de Cargo";
    }
}
