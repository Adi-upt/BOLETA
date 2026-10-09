
package ModeloDAO;

import Config.ClsConexion;
import Interfaces.CRUDDetalle;
import Modelo.clsDetalle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class clsDetalleDAO implements CRUDDetalle {

    private String error;

    public String getError() {
        return error;
    }

    private clsDetalle obtenerDetalle(ResultSet rs) throws SQLException {
        clsDetalle detalle = new clsDetalle();
        detalle.setIdDetalle(rs.getInt("IdDetalle"));
        detalle.setIdBoleta(rs.getObject("IdBoleta", Integer.class));
        detalle.setIdProducto(rs.getObject("IdProducto", Integer.class));
        detalle.setNombre(rs.getString("Nombre"));
        detalle.setCantidad(rs.getInt("Cantidad"));
        detalle.setPunitario(rs.getLong("Punitario"));
        detalle.setImporte(rs.getLong("Importe"));
        return detalle;
    }

    @Override
    public List<clsDetalle> listar() {
        List<clsDetalle> detalles = new ArrayList<>();
        String sql = "SELECT * FROM tbDetalle ORDER BY IdDetalle";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return detalles;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    detalles.add(obtenerDetalle(rs));
                }
            }

        } catch (SQLException e) {
            error = "Error al listar detalles: " + e.getMessage();
            System.out.println(error);
        }

        return detalles;
    }

    @Override
    public clsDetalle list(int id) {
        String sql = "SELECT * FROM tbDetalle WHERE IdDetalle = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return obtenerDetalle(rs);
                }
            }

        } catch (SQLException e) {
            error = "Error al buscar detalle: " + e.getMessage();
            System.out.println(error);
        }

        return null;
    }

    @Override
    public boolean add(clsDetalle detalle) {
        String sql = "INSERT INTO tbDetalle (IdBoleta, IdProducto, Nombre, Cantidad, Punitario, Importe) VALUES (?, ?, ?, ?, ?, ?)";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setObject(1, detalle.getIdBoleta(), Types.INTEGER);
                ps.setObject(2, detalle.getIdProducto(), Types.INTEGER);
                ps.setString(3, detalle.getNombre());
                ps.setInt(4, detalle.getCantidad());
                ps.setLong(5, detalle.getPunitario());
                ps.setLong(6, detalle.getImporte());
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al agregar detalle: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean edit(clsDetalle detalle) {
        String sql = "UPDATE tbDetalle SET IdBoleta = ?, IdProducto = ?, Nombre = ?, Cantidad = ?, Punitario = ?, Importe = ? WHERE IdDetalle = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setObject(1, detalle.getIdBoleta(), Types.INTEGER);
                ps.setObject(2, detalle.getIdProducto(), Types.INTEGER);
                ps.setString(3, detalle.getNombre());
                ps.setInt(4, detalle.getCantidad());
                ps.setLong(5, detalle.getPunitario());
                ps.setLong(6, detalle.getImporte());
                ps.setInt(7, detalle.getIdDetalle());
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al modificar detalle: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public boolean eliminar(int idDetalle) {
        String sql = "DELETE FROM tbDetalle WHERE IdDetalle = ?";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idDetalle);
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            error = "Error al eliminar detalle: " + e.getMessage();
            System.out.println(error);
            return false;
        }
    }

    @Override
    public List<clsDetalle> listarPorBoleta(int idBoleta) {
        List<clsDetalle> detalles = new ArrayList<>();
        String sql = "SELECT * FROM tbDetalle WHERE IdBoleta = ? ORDER BY IdDetalle";
        error = null;

        try (Connection con = new ClsConexion().getConnection()) {
            if (con == null) return detalles;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idBoleta);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        detalles.add(obtenerDetalle(rs));
                    }
                }
            }

        } catch (SQLException e) {
            error = "Error al listar detalles de boleta: " + e.getMessage();
            System.out.println(error);
        }

        return detalles;
    }
}
