
package Controlador;

import Modelo.clsDetalle;
import ModeloDAO.clsDetalleDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControladorDetalle")
public class ControladorDetalle extends HttpServlet {

    private final String listar = "VistaDetalle/listar.jsp";
    private final String add = "VistaDetalle/add.jsp";
    private final String edit = "VistaDetalle/edit.jsp";

    private final clsDetalleDAO dao = new clsDetalleDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("accion");
        if (action == null) action = "listar";

        String acceso;

        try {
            switch (action.toLowerCase()) {
                case "listar":
                    request.setAttribute("detalles", dao.listar());
                    request.setAttribute("error", dao.getError());
                    acceso = listar;
                    break;

                case "add":
                    acceso = add;
                    break;

                case "agregar":
                    clsDetalle nuevo = new clsDetalle();
                    nuevo.setIdBoleta(obtenerId(request.getParameter("txtIdBoleta")));
                    nuevo.setIdProducto(obtenerId(request.getParameter("txtIdProducto")));
                    nuevo.setNombre(request.getParameter("txtNombre"));
                    nuevo.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
                    nuevo.setPunitario(Long.parseLong(request.getParameter("txtPunitario")));
                    nuevo.setImporte(Long.parseLong(request.getParameter("txtImporte")));

                    dao.add(nuevo);
                    response.sendRedirect("ControladorDetalle?accion=listar");
                    return;

                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("detalle", dao.list(id));
                    acceso = edit;
                    break;

                case "actualizar":
                    clsDetalle detalle = new clsDetalle();
                    detalle.setIdDetalle(Integer.parseInt(request.getParameter("txtId")));
                    detalle.setIdBoleta(obtenerId(request.getParameter("txtIdBoleta")));
                    detalle.setIdProducto(obtenerId(request.getParameter("txtIdProducto")));
                    detalle.setNombre(request.getParameter("txtNombre"));
                    detalle.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
                    detalle.setPunitario(Long.parseLong(request.getParameter("txtPunitario")));
                    detalle.setImporte(Long.parseLong(request.getParameter("txtImporte")));

                    dao.edit(detalle);
                    response.sendRedirect("ControladorDetalle?accion=listar");
                    return;

                case "eliminar":
                    int idDetalle = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idDetalle);
                    response.sendRedirect("ControladorDetalle?accion=listar");
                    return;

                case "porboleta":
                    int idBoleta = Integer.parseInt(request.getParameter("idBoleta"));
                    request.setAttribute("detalles", dao.listarPorBoleta(idBoleta));
                    request.setAttribute("idBoleta", idBoleta);
                    acceso = listar;
                    break;

                default:
                    request.setAttribute("detalles", dao.listar());
                    acceso = listar;
                    break;
            }

            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos numéricos del detalle inválidos.");
        }
    }

    private Integer obtenerId(String valor) {
        if (valor == null || valor.trim().isEmpty()) return null;
        return Integer.parseInt(valor);
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
}
