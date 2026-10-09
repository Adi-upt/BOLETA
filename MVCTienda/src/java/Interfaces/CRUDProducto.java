
package Interfaces;

import Modelo.clsProducto;
import java.util.List;

public interface CRUDProducto {
    public List<clsProducto> listar();
    public clsProducto list(int id);
    public boolean add(clsProducto producto);
    public boolean edit(clsProducto producto);
    public boolean eliminar(int idProducto);
    public clsProducto buscarProducto(String codigo);
}
