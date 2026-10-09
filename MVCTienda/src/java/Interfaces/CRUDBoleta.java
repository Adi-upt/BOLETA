
package Interfaces;

import Modelo.clsBoleta;
import java.util.List;

public interface CRUDBoleta {
    public List<clsBoleta> listar();
    public clsBoleta list(int id);
    public boolean add(clsBoleta boleta);
    public boolean edit(clsBoleta boleta);
    public boolean eliminar(int idBoleta);
    public int buscarIdPorNumBol(String numBol);
}
