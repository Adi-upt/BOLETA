
package ModeloDAO;

import Config.ClsConexion;
import Interfaces.CRUDProducto;
import Modelo.clsProducto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class clsProductoDAO implements CRUDProducto {

    private String error;

    public String getError() {
        return error;
    }

    private clsProducto obtenerProducto(ResultSet rs) throws SQLException {
        clsProducto producto = new clsProducto();
        producto.setIdProducto(rs.getInt("IdProducto"));
        producto.setNombre(rs.getString("Nombre"));
        producto.setCantidad(rs.getInt("Cantidad"));
        producto.setPrecio(rs.getLong("Precio"));
        return producto;
    }

    @Override
    public List<clsProducto> listar() {
        List<clsProducto> productos = new ArrayList<>();
        String sql = "SELECT * FROM tbProducto ORDER BY IdProducto";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return productos;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    productos.add(obtenerProducto(rs));
                }
            }

        } catch (SQLException e) {
            error = "Error al listar productos: " + e.getMessage();
            System.out.println(error);
        }

        return productos;
    }

    @Override
    public clsProducto list(int id) {
        String sql = "SELECT * FROM tbProducto WHERE IdProducto = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return obtenerProducto(rs);
                }
            }

        } catch (SQLException e) {
            error = "Error al buscar producto: " + e.getMessage();
            System.out.println(error);
        }

        return null;
    }

    @Override
    public boolean add(clsProducto producto) {
        String sql = "INSERT INTO tbProducto (Nombre, Cantidad, Precio) VALUES (?, ?, ?)";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, producto.getNombre());
                ps.setInt(2, producto.getCantidad());
                ps.setLong(3, producto.getPrecio());
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al agregar producto: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean edit(clsProducto producto) {
        String sql = "UPDATE tbProducto SET Nombre = ?, Cantidad = ?, Precio = ? WHERE IdProducto = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, producto.getNombre());
                ps.setInt(2, producto.getCantidad());
                ps.setLong(3, producto.getPrecio());
                ps.setInt(4, producto.getIdProducto());
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al modificar producto: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM tbProducto WHERE IdProducto = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idProducto);
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al eliminar producto: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public clsProducto buscarProducto(String codigo) {
        try {
            return list(Integer.parseInt(codigo.trim()));
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }
}
