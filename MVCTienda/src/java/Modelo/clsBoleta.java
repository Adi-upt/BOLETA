
package Modelo;

import java.sql.Date;

public class clsBoleta {
    private int idBoleta;
    private String numBol;
    private Date fecha;
    private long total;
    private Integer idEmpleado;
    private Integer idCliente;

    public clsBoleta() {}

    public clsBoleta(int idBoleta, String numBol, Date fecha, long total, Integer idEmpleado, Integer idCliente) {
        this.idBoleta = idBoleta;
        this.numBol = numBol;
        this.fecha = fecha;
        this.total = total;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
    }

    public clsBoleta(int idBoleta, String numBol, long total) {
        this.idBoleta = idBoleta;
        this.numBol = numBol;
        this.total = total;
    }

    public clsBoleta(int idBoleta, String numBol, Date fecha, long total) {
        this.idBoleta = idBoleta;
        this.numBol = numBol;
        this.fecha = fecha;
        this.total = total;
    }

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public String getNumBol() {
        return numBol;
    }

    public void setNumBol(String numBol) {
        this.numBol = numBol;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
}
