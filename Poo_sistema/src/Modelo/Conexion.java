
package Modelo;

import java.sql.DriverManager;
import java.sql.Connection;

public class Conexion {
    Connection con;
    public Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/sistema";
        String user = "root";
        String pass = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
        }
        return con;
    }
}
