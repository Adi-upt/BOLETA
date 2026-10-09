
package Modelo;

public class clsCliente {
    private int idCliente;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;

    public clsCliente() {}

    public clsCliente(int idCliente, String dni, String nombre, String apellido, String telefono) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
}
