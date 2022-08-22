<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="es_CO" />
<%@include file="cache.jsp" %>
<%@include file="validarSesion.jsp" %>
<%@include file="validarRolCliente.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Mis datos</title>
        <jsp:include page="WEB-INF/paginas/comunes/head.jsp" />
    </head>
    <body>
        <jsp:include page="WEB-INF/paginas/comunes/navbar.jsp" />
        <section id="clientes">
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-8 card mb-3 p-3 bg-light">
                        <h4 class="text-center">Mis datos</h4>
                        <p><span class="fw-bold">Nombre: </span>${cuenta.titular}</p>
                        <p><span class="fw-bold">Cedula: </span><fmt:formatNumber value="${cliente.cedulaCliente}" type="number" /></p>
                        <p><span class="fw-bold">Telefono: </span>${cliente.cedulaCliente}</p>
                        <p><span class="fw-bold">Num cuenta: </span>${cuenta.numeroCuenta}</p>
                        <p><span class="fw-bold">Fecha apertura: </span>${cuenta.fechaApertura}</p>
                        <p><span class="fw-bold">Estado: </span>${cuenta.estado}</p>
                    </div>

                    <!-- Tarjetas pra los totales -->
                    <div class="col-md-4">
                        <div class="card text-center bg-danger text-white mb-3 py-3">
                            <div class="card-body">
                                <h3>Saldo total</h3>
                                <h4 class="display-4">
                                    <fmt:formatNumber value="${saldo}" type="currency" />
                                </h4>
                            </div>
                        </div>
                        <button type="button" class="btn-transparent border-0 w-100 bg-transparent px-0" data-bs-toggle="modal" data-bs-target="#retirarDinero">
                            <div class="card text-center bg-success text-white mb-3">
                                <div class="card-body">
                                    <h3>Retirar dinero</h3>
                                    <h4 class="display-4">
                                        <i class="bi bi-wallet2"></i>
                                    </h4>
                                </div>
                            </div>
                        </button>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="WEB-INF/paginas/comunes/footer.jsp" />
    </body>
</html>
<jsp:include page="WEB-INF/paginas/comunes/alerta.jsp" />
<jsp:include page="WEB-INF/paginas/comunes/retirarDinero.jsp" />