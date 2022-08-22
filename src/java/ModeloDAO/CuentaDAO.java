/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import ModeloVO.CuentaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion2;

/**
 *
 * @author Sena
 */
public class CuentaDAO extends Conexion2 {

    private Connection conn;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private String sql;
    boolean operacionExitosa = false;

    private String idCuenta = "", numeroCuenta = "", titular = "", saldo = "", fechaApertura = "", estado = "";

    public CuentaDAO() {
    }

    public CuentaDAO(CuentaVO cuentaVO) {
        super();
        //conectarse a la base de datos
        try {
            conn = this.getConnection();
            //traer al DAO los datos del VO para hacer la operacion y los trae de forma segura
            idCuenta = cuentaVO.getIdCuenta();
            numeroCuenta = cuentaVO.getNumeroCuenta();
            titular = cuentaVO.getTitular();
            saldo = cuentaVO.getSaldo();
            fechaApertura = cuentaVO.getFechaApertura();
            estado = cuentaVO.getEstado();

        } catch (Exception e) {
            //traiga el log de la clase e imprima los errores
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Crear Cuenta
    public int agregarCuenta() {

        int ultimoid = 0;

        try {
            sql = "INSERT INTO `cuenta`( NUMEROCTA, TITULAR, SALDO, FECHAAPERTURA, ESTADO) VALUES (?,?,?,?,?)";
            conn = this.getConnection();
            //crear el puente, prepara lo que va a mandar
            puente = conn.prepareStatement(sql);
            //por el puente manda los datos a insertar
            puente.setString(1, numeroCuenta);
            puente.setString(2, titular);
            puente.setString(3, saldo);
            puente.setString(4, fechaApertura);
            puente.setString(5, estado);

            puente.executeUpdate();

            sql = "SELECT * FROM cuenta ORDER BY  IDCUENTA DESC LIMIT 1 ";
            puente = conn.prepareStatement(sql);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {

                ultimoid = Integer.parseInt(mensajero.getString(1));

            }

        } catch (SQLException e) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);
        } //
        finally {
            try {
                this.close(conn);
            } catch (Exception e) {
                Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return ultimoid;
    }

    // Método para saber si un usuario ya se encuentra registrado
    public boolean cuentaYaExiste(String numCuenta) {
        sql = "SELECT * FROM cuenta WHERE NUMEROCTA = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, numCuenta);
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

    public boolean update(CuentaVO cuentaVO) {

        try {
            sql = "UPDATE cuenta SET ESTADO=? WHERE IDCUENTA=?; ";
            puente = conn.prepareStatement(sql);
            puente.setString(1, estado);
            puente.setString(2, idCuenta);
            puente.executeUpdate();
            operacionExitosa = true;

        } catch (SQLException e) {

            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            //Sentencia para que independientemnte de lo que pase haga eso
            try {

                this.close(conn);
            } catch (Exception e) {

                Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return operacionExitosa;
    }

    public CuentaVO ActualizarCuenta(String Cuenta) {

        CuentaVO cuentaVo = null;

        try {
            conn = this.getConnection();
            sql = "SELECT * FROM cuenta WHERE IDCUENTA = ?";
            puente = conn.prepareStatement(sql);
            puente.setString(1, Cuenta);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {
                cuentaVo = new CuentaVO(mensajero.getString("IDCUENTA"), mensajero.getString("NUMEROCTA"), mensajero.getString("TITULAR"), mensajero.getString("SALDO"), mensajero.getString("FECHAAPERTURA"), mensajero.getString("ESTADO"));
            }
        } catch (SQLException e) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            //Sentencia para que independientemnte de lo que pase haga eso
            this.close(conn);
        }
        return cuentaVo;
    }

    // Método para obtener los datos de una cuenta
    public CuentaVO consultarCuentaPorId(String idCuenta) {
        CuentaVO cuentaVo = null;
        sql = "SELECT * FROM cuenta WHERE idcuenta = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, idCuenta);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {
                cuentaVo = new CuentaVO(mensajero.getString(1), mensajero.getString(2), mensajero.getString(3), mensajero.getString(4), mensajero.getString(5), mensajero.getString(6));
            }
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerramos la conexion
            this.close(conn);
        }

        return cuentaVo;
    }
    
    // Método para obtener los datos de una cuenta
    public String consultarId(String idUsuario) {
        String cuenta= null;
        sql = "SELECT C.IDCUENTA FROM cuenta C INNER JOIN cliente CT ON C.IDCUENTA=CT.IDCLIENTE INNER JOIN usuario U ON CT.IDCLIENTE=U.IDUSUARIO WHERE U.IDUSUARIO = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, idUsuario);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {
              cuenta = mensajero.getString(1);
            }
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerramos la conexion
            this.close(conn);
        }

        return cuenta;
    }

    public boolean update() {

        sql = "UPDATE cuenta SET estado = ? WHERE idcuenta = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, estado);
            puente.setString(2, idCuenta);
            puente.executeUpdate();

            operacionExitosa = true;

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error al actualizar la cuenta: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return operacionExitosa;
    }

    public boolean retirarDinero(String cantidadRetirar) {

        sql = "UPDATE cuenta SET saldo = saldo - ? WHERE idcuenta = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, cantidadRetirar);
            puente.setString(2, idCuenta);
            puente.executeUpdate();

            operacionExitosa = true;

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error al actualizar la cuenta: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return operacionExitosa;
    }

    public boolean delete(String id) {

        sql = "DELETE FROM cuenta WHERE idcuenta = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, id);
            puente.executeUpdate();

            operacionExitosa = true;

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error al eliminar la cuenta: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return operacionExitosa;
    }

    public CuentaVO consultarCuenta(String estado) throws SQLException {

        CuentaVO cuentaVO = null;

        try {
            conn = this.getConnection();

            sql = "SELECT * FROM cuenta WHERE ESTADO=?";
            puente = conn.prepareStatement(sql);
            puente.setString(1, estado);
            mensajero = puente.executeQuery();

            while (mensajero.next()) {

                cuentaVO = new CuentaVO(mensajero.getString("IDCUENTA"), mensajero.getString("NUMEROCTA"), mensajero.getString("TITULAR"), mensajero.getString("SALDO"), mensajero.getString("FECHAAPERTURA"), mensajero.getString("ESTADO"));
            }
        } catch (SQLException e) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            //Sentencia para que independientemnte de lo que pase haga eso
            this.close(conn);
        }
        return cuentaVO;

    }

    public ArrayList<CuentaVO> listarCuenta(String estado) {

        //Crear Array, nombre del objeto + nombre del array 
        ArrayList<CuentaVO> listaCuenta = new ArrayList<>();

        try {
            conn = this.getConnection();
            sql = "SELECT * FROM cuenta WHERE ESTADO=?";
            puente = conn.prepareStatement(sql);
            puente.setString(1, estado);

            mensajero = puente.executeQuery();

            while (mensajero.next()) {

                CuentaVO cuentaVO = new CuentaVO(mensajero.getString(1), mensajero.getString(2), mensajero.getString(3), mensajero.getString(4), mensajero.getString(5), mensajero.getString(6));

                listaCuenta.add(cuentaVO);

            }

        } catch (SQLException e) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            //Sentencia para que independientemnte de lo que pase haga eso
            this.close(conn);
        }

        return listaCuenta;

    }

    //Metodo para LISTAR
    public ArrayList<CuentaVO> listar() {

        //Crear Array, nombre del objeto + nombre del array 
        ArrayList<CuentaVO> listaCuentas = new ArrayList<>();

        try {
            conn = this.getConnection();
            sql = "SELECT * FROM cuenta";
            puente = conn.prepareStatement(sql);
            mensajero = puente.executeQuery();

            while (mensajero.next()) {

                CuentaVO cuentaVO = new CuentaVO(mensajero.getString(1), mensajero.getString(2), mensajero.getString(3), mensajero.getString(4), mensajero.getString(5), mensajero.getString(6));

                listaCuentas.add(cuentaVO);

            }

        } catch (SQLException e) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            //Sentencia para que independientemnte de lo que pase haga eso
            this.close(conn);
        }

        return listaCuentas;

    }

}
