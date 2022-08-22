package ModeloVO;

/**
 *
 * @author Camargo
 */
public class UsuarioVO {
    private String idUsuario;
    private String login;
    private String password;
    private String idRol;

    public UsuarioVO() {
    }

    public UsuarioVO(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioVO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UsuarioVO(String login, String password, String idRol) {
        this.login = login;
        this.password = password;
        this.idRol = idRol;
    }

    public UsuarioVO(String idUsuario, String login, String password, String idRol) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.password = password;
        this.idRol = idRol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return "UsuarioVO{" + "idUsuario=" + idUsuario + ", login=" + login + ", password=" + password + ", idRol=" + idRol + '}';
    }
    
}
