package ModeloDAO;

import Config.ClsConexion;
import Interfaces.CRUDCargo;
import Modelo.clsCargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a datos de la tabla Cargo.
 * @author Hpuser
 */
public class clsCargoDAO implements CRUDCargo {

    private String error;

    public String getError() {
        return error;
    }

    @Override
    public List<clsCargo> listar() {
        List<clsCargo> lista = new ArrayList<>();
        error = null;
        ClsConexion cn = new ClsConexion();
        Connection con = cn.getConnection();
        if (con == null) {
            error = cn.getError();
            return lista;
        }
        String sql = "SELECT idcargo, nombre, descripcion FROM tbcargo ORDER BY idcargo";
        try (Connection c2 = con;
             PreparedStatement ps = c2.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clsCargo c = new clsCargo();
                c.setIdCargo(rs.getInt("idcargo"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                lista.add(c);
            }
        } catch (SQLException e) {
            error = "Error SQL al listar cargos: " + e.getMessage();
            System.out.println(error);
        }
        return lista;
    }

    @Override
    public clsCargo list(int id) {
        clsCargo c = null;
        String sql = "SELECT idcargo, nombre, descripcion FROM tbcargo WHERE idcargo = ?";
        try (Connection con = new ClsConexion().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new clsCargo();
                    c.setIdCargo(rs.getInt("idcargo"));
                    c.setNombre(rs.getString("nombre"));
                    c.setDescripcion(rs.getString("descripcion"));
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Error al buscar cargo -> " + e.getMessage());
        }
        return c;
    }

    @Override
    public boolean add(clsCargo carg) {
        String sql = "INSERT INTO tbcargo (idcargo,nombre, descripcion) VALUES (?, ?, ?)";
        try (Connection con = new ClsConexion().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, carg.getIdCargo());
            ps.setString(2, carg.getNombre());
            ps.setString(3, carg.getDescripcion());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException | NullPointerException e) {
            System.out.println("Error al agregar cargo -> " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean edit(clsCargo carg) {
        String sql = "UPDATE tbcargo SET nombre = ?, descripcion = ? WHERE idcargo = ?";
        try (Connection con = new ClsConexion().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, carg.getNombre());
            ps.setString(2, carg.getDescripcion());
            ps.setInt(3, carg.getIdCargo());
            return ps.executeUpdate() > 0;
        } catch (SQLException | NullPointerException e) {
            System.out.println("Error al editar cargo -> " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idcargo) {
        String sql = "DELETE FROM tbcargo WHERE idcargo = ?";
        try (Connection con = new ClsConexion().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idcargo);
            return ps.executeUpdate() > 0;
        } catch (SQLException | NullPointerException e) {
            System.out.println("Error al eliminar cargo -> " + e.getMessage());
            return false;
        }
    }
}
