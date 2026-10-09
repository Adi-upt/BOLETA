
package Controlador;

import Modelo.clsEmpleado;
import ModeloDAO.clsCargoDAO;
import ModeloDAO.clsEmpleadoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ControladorEmpleado")
public class ControladorEmpleado extends HttpServlet {

    private final String listar = "VistaEmpleado/listar.jsp";
    private final String add = "VistaEmpleado/add.jsp";
    private final String edit = "VistaEmpleado/edit.jsp";
    private final String login = "VistaEmpleado/login.jsp";

    private final clsEmpleadoDAO dao = new clsEmpleadoDAO();

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
                    request.setAttribute("empleados", dao.listar());
                    request.setAttribute("error", dao.getError());
                    acceso = listar;
                    break;

                case "add":
                    request.setAttribute("cargos", new clsCargoDAO().listar());
                    acceso = add;
                    break;

                case "agregar":
                    clsEmpleado nuevo = new clsEmpleado();
                    nuevo.setDNI(request.getParameter("txtDni"));
                    nuevo.setNombre(request.getParameter("txtNombre"));
                    nuevo.setApellido(request.getParameter("txtApellido"));
                    nuevo.setTelefono(request.getParameter("txtTelefono"));
                    nuevo.setIdCargo(obtenerIdCargo(request.getParameter("txtIdCargo")));
                    nuevo.setUsuario(request.getParameter("txtUsuario"));
                    nuevo.setClave(request.getParameter("txtClave"));

                    dao.add(nuevo);
                    response.sendRedirect("ControladorEmpleado?accion=listar");
                    return;

                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("empleado", dao.list(id));
                    request.setAttribute("cargos", new clsCargoDAO().listar());
                    acceso = edit;
                    break;

                case "actualizar":
                    clsEmpleado empleado = new clsEmpleado();
                    empleado.setIdEmpleado(Integer.parseInt(request.getParameter("txtId")));
                    empleado.setDNI(request.getParameter("txtDni"));
                    empleado.setNombre(request.getParameter("txtNombre"));
                    empleado.setApellido(request.getParameter("txtApellido"));
                    empleado.setTelefono(request.getParameter("txtTelefono"));
                    empleado.setIdCargo(obtenerIdCargo(request.getParameter("txtIdCargo")));
                    empleado.setUsuario(request.getParameter("txtUsuario"));
                    empleado.setClave(request.getParameter("txtClave"));

                    dao.edit(empleado);
                    response.sendRedirect("ControladorEmpleado?accion=listar");
                    return;

                case "eliminar":
                    int idEmpleado = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEmpleado);
                    response.sendRedirect("ControladorEmpleado?accion=listar");
                    return;

                case "buscardni":
                    String dni = request.getParameter("dni");
                    clsEmpleado encontrado = dao.buscarPorDni(dni);

                    if (encontrado == null) {
                        request.setAttribute("error", "No se encontró el empleado.");
                        request.setAttribute("empleados", dao.listar());
                        acceso = listar;
                    } else {
                        request.setAttribute("empleado", encontrado);
                        request.setAttribute("cargos", new clsCargoDAO().listar());
                        acceso = edit;
                    }
                    break;

                case "login":
                    acceso = login;
                    break;

                case "iniciarsesion":
                    String usuario = request.getParameter("txtUsuario");
                    String clave = request.getParameter("txtClave");

                    clsEmpleado usuarioActual = new clsEmpleado(usuario, clave);

                    if (dao.iniciarSesion(usuarioActual)) {
                        usuarioActual.setClave(null);
                        HttpSession session = request.getSession();
                        request.changeSessionId();
                        session.setAttribute("empleado", usuarioActual);

                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                        return;
                    }

                    request.setAttribute("error", "Usuario o contraseña incorrectos.");
                    acceso = login;
                    break;

                case "cerrarsesion":
                    HttpSession sesion = request.getSession(false);
                    if (sesion != null) sesion.invalidate();

                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    return;

                default:
                    request.setAttribute("empleados", dao.listar());
                    acceso = listar;
                    break;
            }

            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de empleado o cargo inválido.");
        }
    }

    private Integer obtenerIdCargo(String valor) {
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
