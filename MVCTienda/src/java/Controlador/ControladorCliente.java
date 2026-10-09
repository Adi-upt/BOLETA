
package Controlador;

import Modelo.clsCliente;
import ModeloDAO.clsClienteDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControladorCliente")
public class ControladorCliente extends HttpServlet {

    private final String listar = "VistaCliente/listar.jsp";
    private final String add = "VistaCliente/add.jsp";
    private final String edit = "VistaCliente/edit.jsp";

    private final clsClienteDAO dao = new clsClienteDAO();

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
                    request.setAttribute("clientes", dao.listar());
                    request.setAttribute("error", dao.getError());
                    acceso = listar;
                    break;

                case "add":
                    acceso = add;
                    break;

                case "agregar":
                    clsCliente nuevo = new clsCliente();
                    nuevo.setDNI(request.getParameter("txtDni"));
                    nuevo.setNombre(request.getParameter("txtNombre"));
                    nuevo.setApellido(request.getParameter("txtApellido"));
                    nuevo.setTelefono(request.getParameter("txtTelefono"));

                    dao.add(nuevo);
                    response.sendRedirect("ControladorCliente?accion=listar");
                    return;

                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("cliente", dao.list(id));
                    acceso = edit;
                    break;

                case "actualizar":
                    clsCliente cliente = new clsCliente();
                    cliente.setIdCliente(Integer.parseInt(request.getParameter("txtId")));
                    cliente.setDNI(request.getParameter("txtDni"));
                    cliente.setNombre(request.getParameter("txtNombre"));
                    cliente.setApellido(request.getParameter("txtApellido"));
                    cliente.setTelefono(request.getParameter("txtTelefono"));

                    dao.edit(cliente);
                    response.sendRedirect("ControladorCliente?accion=listar");
                    return;

                case "eliminar":
                    int idCliente = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idCliente);
                    response.sendRedirect("ControladorCliente?accion=listar");
                    return;

                case "buscardni":
                    String dni = request.getParameter("dni");
                    clsCliente encontrado = dao.buscarPorDni(dni);

                    if (encontrado == null) {
                        request.setAttribute("error", "No se encontró el cliente.");
                        request.setAttribute("clientes", dao.listar());
                        acceso = listar;
                    } else {
                        request.setAttribute("cliente", encontrado);
                        acceso = edit;
                    }
                    break;

                default:
                    request.setAttribute("clientes", dao.listar());
                    acceso = listar;
                    break;
            }

            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de cliente inválido.");
        }
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
