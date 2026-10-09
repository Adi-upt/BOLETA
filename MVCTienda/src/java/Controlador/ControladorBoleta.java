
package Controlador;

import Modelo.clsBoleta;
import ModeloDAO.clsBoletaDAO;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControladorBoleta")
public class ControladorBoleta extends HttpServlet {

    private final String listar = "VistaBoleta/listar.jsp";
    private final String add = "VistaBoleta/add.jsp";
    private final String edit = "VistaBoleta/edit.jsp";

    private final clsBoletaDAO dao = new clsBoletaDAO();

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
                    request.setAttribute("boletas", dao.listar());
                    request.setAttribute("error", dao.getError());
                    acceso = listar;
                    break;

                case "add":
                    acceso = add;
                    break;

                case "agregar":
                    clsBoleta nueva = new clsBoleta();
                    nueva.setNumBol(request.getParameter("txtNumBol"));
                    nueva.setFecha(Date.valueOf(request.getParameter("txtFecha")));
                    nueva.setTotal(Long.parseLong(request.getParameter("txtTotal")));
                    nueva.setIdEmpleado(obtenerId(request.getParameter("txtIdEmpleado")));
                    nueva.setIdCliente(obtenerId(request.getParameter("txtIdCliente")));

                    dao.add(nueva);
                    response.sendRedirect("ControladorBoleta?accion=listar");
                    return;

                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("boleta", dao.list(id));
                    acceso = edit;
                    break;

                case "actualizar":
                    clsBoleta boleta = new clsBoleta();
                    boleta.setIdBoleta(Integer.parseInt(request.getParameter("txtId")));
                    boleta.setNumBol(request.getParameter("txtNumBol"));
                    boleta.setFecha(Date.valueOf(request.getParameter("txtFecha")));
                    boleta.setTotal(Long.parseLong(request.getParameter("txtTotal")));
                    boleta.setIdEmpleado(obtenerId(request.getParameter("txtIdEmpleado")));
                    boleta.setIdCliente(obtenerId(request.getParameter("txtIdCliente")));

                    dao.edit(boleta);
                    response.sendRedirect("ControladorBoleta?accion=listar");
                    return;

                case "eliminar":
                    int idBoleta = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idBoleta);
                    response.sendRedirect("ControladorBoleta?accion=listar");
                    return;

                case "buscar":
                    String numBol = request.getParameter("numBol");
                    int idEncontrado = dao.buscarIdPorNumBol(numBol);

                    if (idEncontrado == -1) {
                        request.setAttribute("error", "No se encontró la boleta.");
                        request.setAttribute("boletas", dao.listar());
                        acceso = listar;
                    } else {
                        request.setAttribute("boleta", dao.list(idEncontrado));
                        acceso = edit;
                    }
                    break;

                case "verdetalles":
                    int idVenta = Integer.parseInt(request.getParameter("id"));
                    response.sendRedirect("ControladorDetalle?accion=porBoleta&idBoleta=" + idVenta);
                    return;

                default:
                    request.setAttribute("boletas", dao.listar());
                    acceso = listar;
                    break;
            }

            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);

        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos de la boleta inválidos.");
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
