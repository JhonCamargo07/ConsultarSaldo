<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="es_CO" />
<!-- Modal -->
<div class="modal fade"id="retirarDinero" tabindex="-1" aria-labelledby="agregarPelicula" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title" id="exampleModalLabel">Retirar dinero</h5>
                <button type="button" id="closeModal" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="${pageContext.request.contextPath}/Cuenta" method="POST">
                <div class="modal-body">
                    <div class="mb-3">
                        <p>¿Cúanto vas a retirar?</p>
                        <p>Saldo: <fmt:formatNumber value="${cuenta.saldo}" type="currency" /></p>
                        <div class="mb-3">
                            <label for="catidadRetirar" class="form-label">Cantidad a retirar<span class="text-danger">*</span></label>
                            <input type="number" class="form-control" id="cantidadRetirar" name="catidadRetirar" max="${cuenta.saldo}">
                        </div>
                        <input type="hidden" name="txtidCuenta" value="${cuenta.idCuenta}">
                        <input type="hidden" name="opcion" value="5">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" id="close" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Retirar</button>
                </div>
            </form>
        </div>
    </div>
</div>
</div>