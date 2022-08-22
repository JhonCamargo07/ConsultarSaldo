<%@page import="ModeloVO.UsuarioVO"%>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container">
        <a class="navbar-brand fw-bold text-dark" href="${pageContext.request.contextPath}/"><i class="bi bi-bank me-1"></i>Banco</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <i class="bi bi-list"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link text-dark fw-bold" href="${pageContext.request.contextPath}/">Inicio</a>
                </li>
                <%
                    if (session.getAttribute("usuario") != null) {
                        UsuarioVO usuarioVo = (UsuarioVO) session.getAttribute("usuario");
                        if (usuarioVo.getIdRol().equals("1")) {
                %>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="consultarCuenta.jsp">Consultar Cuentas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="clientes.jsp">Clientes</a>
                </li>
              
                <%                    
                        }
                %>
                <form action="Usuario" method="POST">
                    <input type="hidden" name="opcion" value="2">
                    <button class="btn btn-outline-success" type="submit">Cerrar sesión</button>
                </form>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>