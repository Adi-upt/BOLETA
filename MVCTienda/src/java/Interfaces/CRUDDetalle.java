
package Interfaces;

import Modelo.clsDetalle;
import java.util.List;

public interface CRUDDetalle {
    public List<clsDetalle> listar();
    public clsDetalle list(int id);
    public boolean add(clsDetalle detalle);
    public boolean edit(clsDetalle detalle);
    public boolean eliminar(int idDetalle);
    public List<clsDetalle> listarPorBoleta(int idBoleta);
}
