<%
    // Si la sesion ya esta abierta, lo lleva a menu
    if (session.getAttribute("usuario") != null) {
        response.sendRedirect("menu.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Iniciar sesión</title>
        <jsp:include page="WEB-INF/paginas/comunes/head.jsp" />
    </head>
    <body>
        <jsp:include page="WEB-INF/paginas/comunes/navbar.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h2 class="text-center my-3 fw-bold">Iniciar sesión</h2>
                    <form action="Usuario" method="POST">
                        <div class="mb-3">
                            <label for="email" class="form-label">Correo <span class="text-danger">*</span></label>
                            <input type="email" placeholder="nombre@mail.com" class="form-control" name="emailUsuario" id="email" value="${login}">
                            <input type="hidden" name="opcion" value="1">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password <span class="text-danger">*</span></label>
                            <input type="password" class="form-control" name="passwordUsuario" id="password" value="${password}">
                        </div>
                        <div class="mb-3 text-center">
                            <button type="submit" class="btn btn-primary">Ingresar</button>
                        </div>
                    </form>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
        <jsp:include page="WEB-INF/paginas/comunes/footer.jsp" />
    </body>
</html>
<jsp:include page="WEB-INF/paginas/comunes/alerta.jsp" />