<%-- 
    Document   : registroCuenta
    Created on : 5/08/2022, 11:25:15 a. m.
    Author     : Sena
--%>

<%@include file="cache.jsp" %>
<%@include file="validarSesion.jsp" %>
<%@include file="validarRolAdmin.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="WEB-INF/paginas/comunes/head.jsp"/>
        <title>Registro Cuentas</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/paginas/comunes/navbar.jsp" />

        <div class="container">

            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h3 class="text-center my-2">Registro Cuentas</h3>
                    <form method="post" action="Cuenta">

                        <div class="form-group">
                            <label>Numero Cuenta</label>
                            <input class="form-control" type="text" name="txtNumeroCuenta"><br>
                        </div>

                        <div class="form-group">
                            <label>Titular</label>
                            <input  class="form-control" type="text" name="txtTitular"><br>
                        </div>

                        <div class="form-group">
                            <label>Saldo</label>
                            <input  class="form-control" type="text" name="txtSaldo"><br>
                        </div>

                        <div class="form-group">
                            <label>Fecha Apertura</label>
                            <input  class="form-control" type="date" name="txtFecha"><br>
                        </div>


                        <div class="form-group">
                            <label>Estado</label>
                            <select  class="form-select" name="txtEstado">
                                <option >Abierta</option>
                                <option>Cerrada</option>
                            </select><br> 

                        </div>


                        <div class="form-group">
                            <label>Cedula</label>
                            <input  class="form-control" type="text" name="txtCedula"><br>
                        </div>


                        <div class="form-group">
                            <label>Telefono</label>
                            <input  class="form-control" type="text" name="txtTelefono"><br>
                        </div>


                        <div class="form-group">
                            <label>Correo</label>
                            <input  class="form-control" type="text" name="txtlogin"><br>
                        </div>


                        <div class="form-group">
                            <label>Contraseña</label>
                            <input  class="form-control" type="text" name="txtpassword"><br>
                        </div>

                        <div class="form-group">
                            <label>Rol</label>
                            <select  class="form-select" name="txtIdRol">

                                <option value="2">Cliente</option>
                                <option value="1">Administrador</option>
                            </select><br> 

                        </div>

                        <div class="text-center">
                            <button  class="btn btn-primary">Registrar</button>
                        </div>
                        <input type="hidden" value="1" name="opcion">
                    </form><br>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
        <jsp:include page="WEB-INF/paginas/comunes/footer.jsp" />
    </body>
</html>
<jsp:include page="WEB-INF/paginas/comunes/alerta.jsp" />