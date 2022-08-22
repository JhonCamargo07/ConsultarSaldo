package util;

import java.sql.*;
import java.util.logging.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Camargo
 */
public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/banco";
    private static final String JDBC_USER_NAME = "root";
    private static final String JDBC_PASSWORD = "";
    
    private static BasicDataSource dataSource;
    
    public static DataSource getDataSource(){
        if(dataSource == null){
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER_NAME);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setInitialSize(50);
        }
        return dataSource;
    }
    
    public Connection getConnection() throws SQLException{
        return getDataSource().getConnection();
    }
    
    public void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
