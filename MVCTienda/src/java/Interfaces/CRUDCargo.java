package Interfaces;

import Modelo.clsCargo;
import java.util.List;

/**
 * Operaciones CRUD para la tabla Cargo.
 * @author Hpuser
 */
public interface CRUDCargo {
    public List<clsCargo> listar();
    public clsCargo list(int id);
    public boolean add(clsCargo carg);
    public boolean edit(clsCargo carg);
    public boolean eliminar(int idcargo);
}
