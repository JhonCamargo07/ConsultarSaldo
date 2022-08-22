<%
    String mensajeBoton = "Eliminar cliente";
    String modalStatic = "";
    boolean hayUnClienteConsultado = ((request.getAttribute("cliente") != null) ? true : false);
    boolean seEliminaAlUsuario = ((request.getAttribute("delete") != null) ? true : false);
%>
<!-- Modal -->
<div class="modal fade" <%= modalStatic%> id="eliminarCliente" tabindex="-1" aria-labelledby="agregarPelicula" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title" id="exampleModalLabel">Eliminar cliente</h5>
                <button type="button" id="closeModal" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="${pageContext.request.contextPath}/Cliente" method="POST">
                <div class="modal-body">
                    <div class="mb-3">
                        <p>Nombre: ${cliente.nombreCliente}</p>
                        <p>Cedula ${cliente.cedulaCliente}</p>
                        <p>Telefono ${cliente.telefonoCliente}</p>
                        <p>¿Seguro que desea eliminar a este cliente?</p>

                        <input type="hidden" name="idCliente" value="${cliente.idCliente}">
                        <input type="hidden" name="cedulaCliente" value="${cliente.cedulaCliente}">
                        <input type="hidden" name="opcion" value="4">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" id="close" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary"><%= mensajeBoton%></button>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
<%
    if (hayUnClienteConsultado && seEliminaAlUsuario) {
%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<script>
    window.$('#eliminarCliente').modal('show');
    $(document).on('click', '#closeModal', function () {
        location.href = "clientes.jsp";
    });
    $(document).on('click', '#close', function () {
        location.href = "clientes.jsp";
    });
</script>      
<%
    }
%>
