
package Interfaces;

import Modelo.clsCliente;
import java.util.List;

public interface CRUDCliente {
    public List<clsCliente> listar();
    public clsCliente list(int id);
    public boolean add(clsCliente cliente);
    public boolean edit(clsCliente cliente);
    public boolean eliminar(int idCliente);
    public clsCliente buscarPorDni(String dni);
}
