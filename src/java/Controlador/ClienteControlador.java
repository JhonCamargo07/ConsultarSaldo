package Controlador;

import ModeloDAO.ClienteDAO;
import ModeloDAO.CuentaDAO;
import ModeloDAO.PruebaCuentaDAO;
import ModeloDAO.UsuarioDAO;
import ModeloVO.ClienteVO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author jhona
 */
@WebServlet(name = "ClienteControlador", urlPatterns = {"/Cliente"})
public class ClienteControlador extends HttpServlet {

    private ClienteDAO clienteDao = new ClienteDAO();
    private UsuarioDAO usuarioDao = new UsuarioDAO();
    private CuentaDAO cuentaDao = new CuentaDAO();
    private ClienteVO clienteVo = new ClienteVO();
    private PruebaCuentaDAO pruebaCuentaDao = new PruebaCuentaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idCliente = request.getParameter("idCliente");
        Object[] objetos = pruebaCuentaDao.consultarDatosCliente(idCliente);

        HttpSession sesion = request.getSession();
        sesion.setAttribute("cuenta", objetos[0]);
        sesion.setAttribute("cliente", objetos[1]);
        response.sendRedirect("cliente.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idCliente = request.getParameter("idCliente");
        String cedulaCliente = request.getParameter("cedulaCliente");
        String nombreCliente = request.getParameter("nombreCliente");
        String telefonoCliente = request.getParameter("telefonoCliente");
        int opcion = Integer.parseInt(request.getParameter("opcion"));

        switch (opcion) {
            case 1: // Consultar por id
                if (idCliente.equals("")) {
                    this.generarMensaje(request, response, "Datos erroneos", "Ningún campo puede ser nulo, completelos e intente nuevamente");
                } else {
                    clienteVo.setIdCliente(idCliente);
                    this.consultarClientePorId(request, response);
                }
                break;
            case 2: // Actualizar
                if (idCliente.equals("") || cedulaCliente.equals("") || nombreCliente.equals("") || telefonoCliente.equals("")) {
                    this.generarMensaje(request, response, "Datos erroneos", "Ningún campo puede ser nulo, completelos e intente nuevamente");
                } else {
                    clienteVo.setIdCliente(idCliente);
                    clienteVo.setCedulaCliente(cedulaCliente);
                    clienteVo.setNombreCliente(nombreCliente);
                    clienteVo.setTelefonoCliente(telefonoCliente);
                    this.actualizarCliente(request, response);
                }
                break;
            case 3: // Consultar por cedula
                if (cedulaCliente.equals("")) {
                    this.generarMensaje(request, response, "Datos erroneos", "Ningún campo puede ser nulo, completelos e intente nuevamente");
                } else {
                    this.consultarClientePorCedula(request, response, cedulaCliente);
                }
                break;
            case 4: // Eliminar
                if (idCliente.equals("")) {
                    this.generarMensaje(request, response, "Datos erroneos", "Ningún campo puede ser nulo, completelos e intente nuevamente");
                } else {
                    clienteVo.setIdCliente(idCliente);
                    clienteVo.setCedulaCliente(cedulaCliente);
                    this.eliminarCliente(request, response);
                }
                break;
            default:
                request.getRequestDispatcher("clientes.jsp").forward(request, response);
        }
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Se consulta que el usuario exista
        ClienteVO clientVo = clienteDao.selectById(clienteVo.getIdCliente());

        if (clientVo != null) {
            // Consultamos si nueva cedula ya esta en uso o si es la misma de la bd
            if (!clienteDao.clienteYaExiste(clienteVo.getCedulaCliente()) || clienteVo.getCedulaCliente().equals(clientVo.getCedulaCliente())) {
                // Si la cedula no esta registrada o no es diferente a la que esta en la BD, continua con la actualizacion
                if (clienteDao.update(clienteVo)) {
                    // Si existe la cedula, se actualizan los datos
                    this.generarMensaje(request, response, "Actualización exitosa", "El cliente se actualizó correctamente");
                } else {
                    this.generarMensaje(request, response, "Actualización fallida", "No se pudo actualizar el cliente, recargue e intente nuevamente");
                }
            } else {
                this.generarMensaje(request, response, "Actualización fallida", "No se pudo actualizar el cliente, porque la cedula ya se encuntra registrada");
            }
        } else {
            this.generarMensaje(request, response, "Actualización fallida", "No se pudo actualizar el cliente, recargue e intente nuevamente");
        }

    }

    private void generarMensaje(HttpServletRequest request, HttpServletResponse response, String titulo, String mensaje) throws ServletException, IOException {
        request.setAttribute("titulo", titulo);
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }

    private void consultarClientePorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        clienteVo = clienteDao.selectById(clienteVo.getIdCliente());

        if (clienteVo != null) {
            request.setAttribute("cliente", clienteVo);
            request.getRequestDispatcher("clientes.jsp").forward(request, response);
        } else {
            this.generarMensaje(request, response, "No se encontro al cliente", "No encontramos a ningun usuario, recargue e intenta nuevamente");
        }
    }

    // Método para consultar los clientes (por cedula) antes de eliminarlos 
    private void consultarClientePorCedula(HttpServletRequest request, HttpServletResponse response, String cedulaCliente) throws ServletException, IOException {
        clienteVo = clienteDao.selectByCC(cedulaCliente);

        if (clienteVo != null) {
            request.setAttribute("cliente", clienteVo);
            request.setAttribute("delete", true); // Para mostrar un respectivo modal
            request.getRequestDispatcher("clientes.jsp").forward(request, response);
        } else {
            this.generarMensaje(request, response, "No se encontro al cliente", "No encontramos a ningun usuario, recargue e intenta nuevamente");
        }
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Consultamos si la cedula existe en la base de datos
        ClienteVO usuVo = clienteDao.selectByCC(clienteVo.getCedulaCliente());

        if (usuVo != null) {
            // Si la cedula existe, se procede a hacer las eliminaciones respectivas (cliente, usuario, cuenta)
            if (clienteDao.delete(clienteVo) && usuarioDao.delete(clienteVo.getIdUsuario()) && cuentaDao.delete(clienteVo.getIdCuenta())) {
                this.generarMensaje(request, response, "Eliminación exitosa", "El cliente se eliminó correctamente");
            } else {
                this.generarMensaje(request, response, "Eliminacion fallida", "No se pudo eliminar el cliente, recargue e intente nuevamente");
            }
        } else {
            this.generarMensaje(request, response, "Eliminacion fallida", "No se pudo eliminar el cliente, recargue e intente nuevamente");
        }
    }
}
