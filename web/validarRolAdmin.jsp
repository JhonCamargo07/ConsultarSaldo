<%@page import="ModeloVO.UsuarioVO"%>
<%
    HttpSession sesion = request.getSession();
    
    UsuarioVO usuarioVo = (UsuarioVO) sesion.getAttribute("usuario");
    
    int idRol = Integer.parseInt(usuarioVo.getIdRol());
    
    if(idRol != 1){
        response.sendRedirect("menu.jsp");
    }

%>
