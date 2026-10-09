
package Modelo;

public class clsDetalle {
    private int idDetalle;
    private Integer idBoleta;
    private Integer idProducto;
    private String nombre;
    private int cantidad;
    private long punitario;
    private long importe;

    public clsDetalle() {}

    public clsDetalle(int idDetalle, Integer idBoleta, Integer idProducto, String nombre, int cantidad, long punitario, long importe) {
        this.idDetalle = idDetalle;
        this.idBoleta = idBoleta;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.punitario = punitario;
        this.importe = importe;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(Integer idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getPunitario() {
        return punitario;
    }

    public void setPunitario(long punitario) {
        this.punitario = punitario;
    }

    public long getImporte() {
        return importe;
    }

    public void setImporte(long importe) {
        this.importe = importe;
    }
}
