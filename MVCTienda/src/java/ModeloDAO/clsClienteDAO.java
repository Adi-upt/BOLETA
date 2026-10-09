
package ModeloDAO;

import Config.ClsConexion;
import Interfaces.CRUDCliente;
import Modelo.clsCliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class clsClienteDAO implements CRUDCliente {

    private String error;

    public String getError() {
        return error;
    }

    private clsCliente obtenerCliente(ResultSet rs) throws SQLException {
        clsCliente cliente = new clsCliente();
        cliente.setIdCliente(rs.getInt("IdCliente"));
        cliente.setDNI(rs.getString("DNI"));
        cliente.setNombre(rs.getString("Nombre"));
        cliente.setApellido(rs.getString("Apellido"));
        cliente.setTelefono(rs.getString("Telefono"));
        return cliente;
    }

    @Override
    public List<clsCliente> listar() {
        List<clsCliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM tbCliente ORDER BY IdCliente";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return clientes;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    clientes.add(obtenerCliente(rs));
                }
            }

        } catch (SQLException e) {
            error = "Error al listar clientes: " + e.getMessage();
            System.out.println(error);
        }

        return clientes;
    }

    @Override
    public clsCliente list(int id) {
        String sql = "SELECT * FROM tbCliente WHERE IdCliente = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return obtenerCliente(rs);
                }
            }

        } catch (SQLException e) {
            error = "Error al buscar cliente: " + e.getMessage();
            System.out.println(error);
        }

        return null;
    }

    @Override
    public boolean add(clsCliente cliente) {
        String sql = "INSERT INTO tbCliente (DNI, Nombre, Apellido, Telefono) VALUES (?, ?, ?, ?)";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, cliente.getDNI());
                ps.setString(2, cliente.getNombre());
                ps.setString(3, cliente.getApellido());
                ps.setString(4, cliente.getTelefono());
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al agregar cliente: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean edit(clsCliente cliente) {
        String sql = "UPDATE tbCliente SET DNI = ?, Nombre = ?, Apellido = ?, Telefono = ? WHERE IdCliente = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, cliente.getDNI());
                ps.setString(2, cliente.getNombre());
                ps.setString(3, cliente.getApellido());
                ps.setString(4, cliente.getTelefono());
                ps.setInt(5, cliente.getIdCliente());
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al modificar cliente: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean eliminar(int idCliente) {
        String sql = "DELETE FROM tbCliente WHERE IdCliente = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idCliente);
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al eliminar cliente: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public clsCliente buscarPorDni(String dni) {
        String sql = "SELECT * FROM tbCliente WHERE DNI = ? LIMIT 1";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, dni);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return obtenerCliente(rs);
                }
            }

        } catch (SQLException e) {
            error = "Error al buscar cliente por DNI: " + e.getMessage();
            System.out.println(error);
        }

        return null;
    }
}

