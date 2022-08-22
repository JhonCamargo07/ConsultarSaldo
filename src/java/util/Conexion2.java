package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sena
 */
public class Conexion2 {
    //    //Declarar variables
    private String driver, userDB, passwordDB, dataBase, urlDB;

    //Objeto de elemento conexion, es el que almacena la conexion 
    private Connection conexion;

    //Constructor.
    public Conexion2() {

        // Asignar valores
        driver = "com.mysql.cj.jdbc.Driver";
        userDB = "root";
        passwordDB = "";
        dataBase = "banco";
        urlDB = "jdbc:mysql://localhost:3306/" + dataBase;

        //Conectarse 
        //El try es una forma de controlar errores 
        try {

            //Creando nueva instancia del driver
            Class.forName(driver).newInstance();
            conexion = DriverManager.getConnection(urlDB, userDB, passwordDB);
            System.out.println("Conexión ok!");

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {

            System.out.println("Error al conectarse" + e.toString());
        }
    }

    //Metodos tipo objeto ( para obtener la conexion y otro para cerrarla)
    public Connection getConnection() {
        return conexion;
    }

    public Connection close(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion = null;
        return conexion;
    }
    public Connection close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion = null;
        return conexion;
    }

    public static void main(String[] args) {
        new Conexion();
    }
}

