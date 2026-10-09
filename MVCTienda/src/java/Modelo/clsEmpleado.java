
package Modelo;

public class clsEmpleado {
    private int idEmpleado;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private Integer idCargo;
    private String usuario;
    private String clave;

    public clsEmpleado() {}

    public clsEmpleado(int idEmpleado, String dni, String nombre, String apellido, String telefono, Integer idCargo, String usuario, String clave) {
        this.idEmpleado = idEmpleado;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.idCargo = idCargo;
        this.usuario = usuario;
        this.clave = clave;
    }

    public clsEmpleado(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
