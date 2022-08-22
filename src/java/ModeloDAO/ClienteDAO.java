package ModeloDAO;

import ModeloVO.ClienteVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion2;

/**
 *
 * @author Sena
 */
public class ClienteDAO extends Conexion2 {

    private Connection conn;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private String sql = "";
    boolean operacionExitosa = false;

    public ClienteDAO() {
    }

    public List<ClienteVO> select() {
        ClienteVO clienteVo = null;
        List<ClienteVO> clientes = new ArrayList();

        sql = "SELECT * FROM cliente";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            mensajero = puente.executeQuery();

            while (mensajero.next()) {
                clienteVo = new ClienteVO(mensajero.getString(1), mensajero.getString(2), mensajero.getString(3), mensajero.getString(4), mensajero.getString(5), mensajero.getString(6));
                clientes.add(clienteVo);
            }
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return clientes;

    }

    public ClienteVO selectById(String id) {
        ClienteVO clienteVo = null;

        sql = "SELECT * FROM cliente WHERE IDCLIENTE = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, id);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {
                clienteVo = new ClienteVO(mensajero.getString(1), mensajero.getString(2), mensajero.getString(3), mensajero.getString(4), mensajero.getString(5), mensajero.getString(6));
            }
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return clienteVo;

    }
    
    public ClienteVO selectByCC(String cedulaCliente) {
        ClienteVO clienteVo = null;

        sql = "SELECT * FROM cliente WHERE cedulacliente = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, cedulaCliente);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {
                clienteVo = new ClienteVO(mensajero.getString(1), mensajero.getString(2), mensajero.getString(3), mensajero.getString(4), mensajero.getString(5), mensajero.getString(6));
            }
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return clienteVo;

    }
    
    public ClienteVO selectByIdUsuario(String idUsuario) {
        ClienteVO clienteVo = null;

        sql = "SELECT * FROM cliente WHERE idusuario = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, idUsuario);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {
                clienteVo = new ClienteVO(mensajero.getString(1), mensajero.getString(2), mensajero.getString(3), mensajero.getString(4), mensajero.getString(5), mensajero.getString(6));
            }
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return clienteVo;

    }

    // Método para saber si un usuario ya se encuentra registrado
    public boolean clienteYaExiste(String cedula) {
        sql = "SELECT * FROM cliente WHERE CEDULACLIENTE = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, cedula);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {
                // Si entra al if es porque si existe
                operacionExitosa = true;
            }
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerramos la conexion
            this.close(conn);
        }

        return operacionExitosa;
    }

    public boolean Insert(ClienteVO clienteVo) {

        sql = "INSERT INTO cliente VALUES (null, ?,?,?,?,?)";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, clienteVo.getCedulaCliente());
            puente.setString(2, clienteVo.getNombreCliente());
            puente.setString(3, clienteVo.getTelefonoCliente());
            puente.setString(4, clienteVo.getIdCuenta());
            puente.setString(5, clienteVo.getIdUsuario());
            puente.executeUpdate();

            operacionExitosa = true;

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error al registrar el cliente: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return operacionExitosa;
    }
    
    public boolean update(ClienteVO clienteVo) {

        sql = "UPDATE cliente SET cedulacliente = ?, nombre = ?, telefono = ? WHERE idcliente = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, clienteVo.getCedulaCliente());
            puente.setString(2, clienteVo.getNombreCliente());
            puente.setString(3, clienteVo.getTelefonoCliente());
            puente.setString(4, clienteVo.getIdCliente());
            puente.executeUpdate();

            operacionExitosa = true;

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error al actualizar el cliente: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return operacionExitosa;
    }
    
    public boolean delete(ClienteVO clienteVo) {

        sql = "DELETE FROM cliente WHERE idcliente = ? and cedulacliente = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, clienteVo.getIdCliente());
            puente.setString(2, clienteVo.getCedulaCliente());
            puente.executeUpdate();

            operacionExitosa = true;

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error al eliminar el cliente: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return operacionExitosa;
    }
}
