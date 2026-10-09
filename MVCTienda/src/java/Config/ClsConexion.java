package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de conexión a MySQL (base de datos dbtiendaupt).
 * @author HP
 */
public class ClsConexion {

    private static final String URL  = "jdbc:mysql://172.30.105.9/dbtiendaupt?useSSL=false&serverTimezone=America/Lima&allowPublicKeyRetrieval=true";
    private static final String USER = "matteo";
    private static final String PASS = "wawawa";

    private Connection con = null;
    private String error = null;

    public ClsConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            error = "No se encontró el conector MySQL (mysql-connector-j.jar): " + e.getMessage();
            System.out.println(error);
        } catch (SQLException e) {
            error = "No se pudo conectar a MySQL: " + e.getMessage();
            System.out.println(error);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public String getError() {
        return error;
    }
}
