<%@include file="cache.jsp" %>
<%@include file="validarSesion.jsp" %>
<%@page import="ModeloVO.UsuarioVO"%>
<%    // Aquí lo que se hace es redirigir a cierta pagina dependiendo del rol que tenga
    UsuarioVO usuarioVo = null;

    usuarioVo = (UsuarioVO) session.getAttribute("usuario");

    out.print(usuarioVo);
    String urlPaginaCliente = "Cliente?idCliente=" + usuarioVo.getIdUsuario();

//    switch (Integer.parseInt(usuarioVo.getIdRol())) {
//        case 1:
//            // Si entra aquí, es un admin
////            out.print("Es admin");
//            response.sendRedirect("registroCuenta.jsp");
//            break;
//        default:
//            // Es un cliente
//            out.print("No es admin");
    response.sendRedirect(urlPaginaCliente);
//    }

%>