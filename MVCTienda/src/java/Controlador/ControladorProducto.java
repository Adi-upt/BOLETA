
package Controlador;

import Modelo.clsProducto;
import ModeloDAO.clsProductoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControladorProducto")
public class ControladorProducto extends HttpServlet {

    private final String listar = "VistaProducto/listar.jsp";
    private final String add = "VistaProducto/add.jsp";
    private final String edit = "VistaProducto/edit.jsp";

    private final clsProductoDAO dao = new clsProductoDAO();

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
                    request.setAttribute("productos", dao.listar());
                    request.setAttribute("error", dao.getError());
                    acceso = listar;
                    break;

                case "add":
                    acceso = add;
                    break;

                case "agregar":
                    clsProducto nuevo = new clsProducto();
                    nuevo.setNombre(request.getParameter("txtNombre"));
                    nuevo.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
                    nuevo.setPrecio(Long.parseLong(request.getParameter("txtPrecio")));

                    dao.add(nuevo);
                    response.sendRedirect("ControladorProducto?accion=listar");
                    return;

                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("producto", dao.list(id));
                    acceso = edit;
                    break;

                case "actualizar":
                    clsProducto producto = new clsProducto();
                    producto.setIdProducto(Integer.parseInt(request.getParameter("txtId")));
                    producto.setNombre(request.getParameter("txtNombre"));
                    producto.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
                    producto.setPrecio(Long.parseLong(request.getParameter("txtPrecio")));

                    dao.edit(producto);
                    response.sendRedirect("ControladorProducto?accion=listar");
                    return;

                case "eliminar":
                    int idProducto = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idProducto);
                    response.sendRedirect("ControladorProducto?accion=listar");
                    return;

                case "buscar":
                    String codigo = request.getParameter("codigo");
                    clsProducto encontrado = dao.buscarProducto(codigo);

                    if (encontrado == null) {
                        request.setAttribute("error", "No se encontró el producto.");
                        request.setAttribute("productos", dao.listar());
                        acceso = listar;
                    } else {
                        request.setAttribute("producto", encontrado);
                        acceso = edit;
                    }
                    break;

                default:
                    request.setAttribute("productos", dao.listar());
                    acceso = listar;
                    break;
            }

            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos numéricos del producto inválidos.");
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
