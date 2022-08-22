package Controlador;

import ModeloDAO.CuentaDAO;
import ModeloDAO.UsuarioDAO;
import ModeloVO.UsuarioVO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.ws.WebServiceRef;
import wsl.WSLConsultarSaldo_Service;

/**
 *
 * @author jhona
 */
@WebServlet(name = "UsuarioControlador", urlPatterns = {"/Usuario"})
public class UsuarioControlador extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ConsultarSaldo_WSL/WSL_ConsultarSaldo.wsdl")
    private WSLConsultarSaldo_Service service;
    
    

    // Objetos globales
    private UsuarioDAO usuarioDao = new UsuarioDAO();
    private UsuarioVO usuarioVo = new UsuarioVO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Resivimos los datos del formulario
        String idUsuario = request.getParameter("idUsuario");
        String login = request.getParameter("emailUsuario");
        String password = request.getParameter("passwordUsuario");
        String idRol = request.getParameter("idRolUsuario");
        int opcion = Integer.parseInt(request.getParameter("opcion"));

        switch (opcion) {
            case 1: // Login
                if (password.equals("") || login.equals("")) {
                    this.generarMensaje(request, response, "Datos erroneos", "Ningún campo puede ser nulo, completelos e intente nuevamente");
                } else {
                    this.login(request, response, login, password);
                }
                break;
            case 2: // Salir
                this.logout(request, response);
                break;
            default:
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response, String login, String password) throws IOException, ServletException {

        if (iniciarSesion(login, password)) {
            usuarioVo = usuarioDao.login(login, password);
            CuentaDAO cuentadao = new CuentaDAO();
           double saldo = consultarDinero(cuentadao.consultarId(usuarioVo.getIdUsuario()));
           
           
            if (usuarioVo != null) {
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("usuario", usuarioVo);
                sesion.setAttribute("saldo", saldo);
                response.sendRedirect("menu.jsp");
            } else {
                request.setAttribute("login", login);
                request.setAttribute("password", password);
                this.generarMensaje(request, response, "Datos erroneos", "No se encontraron registros con los datos suministrados");
            }
        } else {
            request.setAttribute("login", login);
            request.setAttribute("password", password);
            this.generarMensaje(request, response, "Datos erroneos", "No se encontraron registros con los datos suministrados");
        }

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);

        HttpSession sesion = request.getSession();
        sesion.removeAttribute("usuario");

        sesion.invalidate();

        this.generarMensaje(request, response, "Sesion cerrada", "La sesión se cerró con exito, sigue disfrutando de nuestros servicios");
    }

    private void generarMensaje(HttpServletRequest request, HttpServletResponse response, String titulo, String mensaje) throws ServletException, IOException {
        request.setAttribute("titulo", titulo);
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private boolean iniciarSesion(java.lang.String email, java.lang.String pass) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsl.WSLConsultarSaldo port = service.getWSLConsultarSaldoPort();
        return port.iniciarSesion(email, pass);
    }

    private double consultarDinero(java.lang.String id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsl.WSLConsultarSaldo port = service.getWSLConsultarSaldoPort();
        return port.consultarDinero(id);
    }
}
