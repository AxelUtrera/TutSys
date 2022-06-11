package tutSys.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public static final String MANEJADOR = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE = "tutsys";
    public static final String HOSTNAME = "localhost";
    public static final String PORT = "3306";
    public static final String URL_CONEXION = "jdbc:mysql://"+HOSTNAME+":"+PORT+"/"+DATABASE+"?serverTimezone=UTC";;

    public static final String USERNAME = "pax";
    public static final String PASSWORD = "daniel98";

    public static Connection abrirConexionBD(){
        Connection conexionBD = null;
        try{
            Class.forName(MANEJADOR);
            conexionBD = DriverManager.getConnection(URL_CONEXION, USERNAME, PASSWORD);
        }catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conexionBD;
    }

}
