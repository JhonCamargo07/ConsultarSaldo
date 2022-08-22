/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import ModeloVO.ClienteVO;
import ModeloVO.CuentaVO;
import ModeloVO.UsuarioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.Conexion2;

/**
 *
 * @author jhona
 */
public class PruebaCuentaDAO extends Conexion2 {

    private Connection conn;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private String sql;
    boolean operacionExitosa = false;

    private CuentaDAO cuentadao = new CuentaDAO();
    private ClienteDAO clientedao = new ClienteDAO();
    private UsuarioDAO usudao = new UsuarioDAO();

    //Se ingresa una cuenta, usuario y cliente
    public boolean crearCuenta(CuentaVO cuentaVO, ClienteVO clienteVO, UsuarioVO usuarioVO) {

        cuentadao = new CuentaDAO(cuentaVO);
        int idcuenta = cuentadao.agregarCuenta();

        if (idcuenta > 0) {

            int idusu = usudao.Insert(usuarioVO);

            if (idusu > 0) {
                
                clienteVO.setIdCuenta(String.valueOf(idcuenta));
                clienteVO.setIdUsuario(String.valueOf(idusu));
                

                if (clientedao.Insert(clienteVO)) {
                    
                    operacionExitosa = true;

                }

            }

        }

        return operacionExitosa;
    }
    
    public Object[] consultarDatosCliente(String idUsuario){
        // Se crea un array para luego asignarle los objetos
        Object[] datosCliente = null;
        
        // Instancias necesarias
        CuentaVO cuentaVo = null;
        ClienteVO clienteVo = null;
        
        // Se obtiene los datos de la consulta
        clienteVo = clientedao.selectByIdUsuario(idUsuario);
        cuentaVo = cuentadao.consultarCuentaPorId(clienteVo.getIdCuenta());
        
        // Se asignan los objetos al array
        datosCliente = new Object[]{cuentaVo, clienteVo};
        return datosCliente;
    }

}
