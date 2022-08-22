package ModeloVO;

/**
 *
 * @author Sena
 */
public class ClienteVO {
    
    private String idCliente;
    private String cedulaCliente;
    private String nombreCliente;
    private String telefonoCliente;
    private String idCuenta;
    private String idUsuario;
    

    public ClienteVO() {
    }

    public ClienteVO(String idCliente) {
        this.idCliente = idCliente;
    }

    public ClienteVO(String cedulaCliente, String nombreCliente, String telefonoCliente, String idCuenta, String idUsuario) {
        this.cedulaCliente = cedulaCliente;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.idCuenta = idCuenta;
        this.idUsuario = idUsuario;
    }

    public ClienteVO(String idCliente, String cedulaCliente, String nombreCliente, String telefonoCliente, String idCuenta, String idUsuario) {
        this.idCliente = idCliente;
        this.cedulaCliente = cedulaCliente;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.idCuenta = idCuenta;
        this.idUsuario = idUsuario;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "ClienteVO{" + "idCliente=" + idCliente + ", cedulaCliente=" + cedulaCliente + ", nombreCliente=" + nombreCliente + ", telefonoCliente=" + telefonoCliente + ", idCuenta=" + idCuenta + ", idUsuario=" + idUsuario + '}';
    }

}
