
package ModeloDAO;

import Config.ClsConexion;
import Interfaces.CRUDBoleta;
import Modelo.clsBoleta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class clsBoletaDAO implements CRUDBoleta {

    private String error;

    public String getError() {
        return error;
    }

    private clsBoleta obtenerBoleta(ResultSet rs) throws SQLException {
        clsBoleta boleta = new clsBoleta();
        boleta.setIdBoleta(rs.getInt("IdBoleta"));
        boleta.setNumBol(rs.getString("NumBol"));
        boleta.setFecha(rs.getDate("Fecha"));
        boleta.setTotal(rs.getLong("Total"));
        boleta.setIdEmpleado(rs.getObject("IdEmpleado", Integer.class));
        boleta.setIdCliente(rs.getObject("IdCliente", Integer.class));
        return boleta;
    }

    @Override
    public List<clsBoleta> listar() {
        List<clsBoleta> boletas = new ArrayList<>();
        String sql = "SELECT * FROM tbBoleta ORDER BY IdBoleta";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return boletas;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    boletas.add(obtenerBoleta(rs));
                }
            }

        } catch (SQLException e) {
            error = "Error al listar boletas: " + e.getMessage();
            System.out.println(error);
        }

        return boletas;
    }

    @Override
    public clsBoleta list(int id) {
        String sql = "SELECT * FROM tbBoleta WHERE IdBoleta = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return obtenerBoleta(rs);
                }
            }

        } catch (SQLException e) {
            error = "Error al buscar boleta: " + e.getMessage();
            System.out.println(error);
        }

        return null;
    }

    @Override
    public boolean add(clsBoleta boleta) {
        String sql = "INSERT INTO tbBoleta (NumBol, Fecha, Total, IdEmpleado, IdCliente) VALUES (?, ?, ?, ?, ?)";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, boleta.getNumBol());
                ps.setDate(2, boleta.getFecha());
                ps.setLong(3, boleta.getTotal());
                ps.setObject(4, boleta.getIdEmpleado(), Types.INTEGER);
                ps.setObject(5, boleta.getIdCliente(), Types.INTEGER);
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al agregar boleta: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean edit(clsBoleta boleta) {
        String sql = "UPDATE tbBoleta SET NumBol = ?, Fecha = ?, Total = ?, IdEmpleado = ?, IdCliente = ? WHERE IdBoleta = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, boleta.getNumBol());
                ps.setDate(2, boleta.getFecha());
                ps.setLong(3, boleta.getTotal());
                ps.setObject(4, boleta.getIdEmpleado(), Types.INTEGER);
                ps.setObject(5, boleta.getIdCliente(), Types.INTEGER);
                ps.setInt(6, boleta.getIdBoleta());
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al modificar boleta: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean eliminar(int idBoleta) {
        String sql = "DELETE FROM tbBoleta WHERE IdBoleta = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idBoleta);
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al eliminar boleta: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public int buscarIdPorNumBol(String numBol) {
        String sql = "SELECT IdBoleta FROM tbBoleta WHERE NumBol = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return -1;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, numBol);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return rs.getInt("IdBoleta");
                }
            }

        } catch (SQLException e) {
            error = "Error al buscar ID de boleta: " + e.getMessage();
            System.out.println(error);
        }

        return -1;
    }
}
