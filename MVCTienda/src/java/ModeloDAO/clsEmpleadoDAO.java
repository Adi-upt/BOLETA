
package ModeloDAO;

import Config.ClsConexion;
import Interfaces.CRUDEmpleado;
import Modelo.clsEmpleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class clsEmpleadoDAO implements CRUDEmpleado {

    private String error;

    public String getError() {
        return error;
    }

    private clsEmpleado obtenerEmpleado(ResultSet rs) throws SQLException {
        clsEmpleado empleado = new clsEmpleado();
        empleado.setIdEmpleado(rs.getInt("IdEmpleado"));
        empleado.setDNI(rs.getString("DNI"));
        empleado.setNombre(rs.getString("Nombre"));
        empleado.setApellido(rs.getString("Apellido"));
        empleado.setTelefono(rs.getString("Telefono"));
        empleado.setIdCargo(rs.getObject("IdCargo", Integer.class));
        empleado.setUsuario(rs.getString("Usuario"));
        empleado.setClave(rs.getString("Clave"));
        return empleado;
    }

    @Override
    public List<clsEmpleado> listar() {
        List<clsEmpleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM tbEmpleado ORDER BY IdEmpleado";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return empleados;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    empleados.add(obtenerEmpleado(rs));
                }
            }

        } catch (SQLException e) {
            error = "Error al listar empleados: " + e.getMessage();
            System.out.println(error);
        }

        return empleados;
    }

    @Override
    public clsEmpleado list(int id) {
        String sql = "SELECT * FROM tbEmpleado WHERE IdEmpleado = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return obtenerEmpleado(rs);
                }
            }

        } catch (SQLException e) {
            error = "Error al buscar empleado: " + e.getMessage();
            System.out.println(error);
        }

        return null;
    }

    @Override
    public boolean add(clsEmpleado empleado) {
        String sql = "INSERT INTO tbEmpleado (DNI, Nombre, Apellido, Telefono, IdCargo, Usuario, Clave) VALUES (?, ?, ?, ?, ?, ?, ?)";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, empleado.getDNI());
                ps.setString(2, empleado.getNombre());
                ps.setString(3, empleado.getApellido());
                ps.setString(4, empleado.getTelefono());
                ps.setObject(5, empleado.getIdCargo(), Types.INTEGER);
                ps.setString(6, empleado.getUsuario());
                ps.setString(7, empleado.getClave());
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al agregar empleado: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean edit(clsEmpleado empleado) {
        String sql = "UPDATE tbEmpleado SET DNI = ?, Nombre = ?, Apellido = ?, Telefono = ?, IdCargo = ?, Usuario = ?, Clave = ? WHERE IdEmpleado = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, empleado.getDNI());
                ps.setString(2, empleado.getNombre());
                ps.setString(3, empleado.getApellido());
                ps.setString(4, empleado.getTelefono());
                ps.setObject(5, empleado.getIdCargo(), Types.INTEGER);
                ps.setString(6, empleado.getUsuario());
                ps.setString(7, empleado.getClave());
                ps.setInt(8, empleado.getIdEmpleado());
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al modificar empleado: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean eliminar(int idEmpleado) {
        String sql = "DELETE FROM tbEmpleado WHERE IdEmpleado = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idEmpleado);
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al eliminar empleado: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean iniciarSesion(clsEmpleado empleado) {
        String sql = "{CALL SP_AutenticarEmpleado(?, ?)}";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (CallableStatement cs = con.prepareCall(sql)) {
                cs.setString(1, empleado.getUsuario());
                cs.setString(2, empleado.getClave());

                try (ResultSet rs = cs.executeQuery()) {
                    if (rs.next()) {
                        empleado.setIdEmpleado(rs.getInt("IdEmpleado"));
                        empleado.setNombre(rs.getString("Nombre"));
                        empleado.setApellido(rs.getString("Apellido"));
                        empleado.setIdCargo(rs.getObject("IdCargo", Integer.class));

                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            error = "Error al iniciar sesión: " + e.getMessage();
            System.out.println(error);
        }

        return false;
    }

    @Override
    public clsEmpleado buscarPorDni(String dni) {
        String sql = "SELECT * FROM tbEmpleado WHERE DNI = ? LIMIT 1";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, dni);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return obtenerEmpleado(rs);
                }
            }

        } catch (SQLException e) {
            error = "Error al buscar empleado por DNI: " + e.getMessage();
            System.out.println(error);
        }

        return null;
    }

    @Override
    public String buscarNombreCargo(clsEmpleado empleado) {
        String sql = "SELECT Nombre FROM tbCargo WHERE IdCargo = ?";
        error = null;

        if (empleado.getIdCargo() == null) return "NO EXISTE";

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return "NO EXISTE";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, empleado.getIdCargo());

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return rs.getString("Nombre");
                }
            }

        } catch (SQLException e) {
            error = "Error al buscar nombre del cargo: " + e.getMessage();
            System.out.println(error);
        }

        return "NO EXISTE";
    }
}

