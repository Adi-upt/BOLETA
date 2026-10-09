
package Interfaces;

import Modelo.clsEmpleado;
import java.util.List;

public interface CRUDEmpleado {
    public List<clsEmpleado> listar();
    public clsEmpleado list(int id);
    public boolean add(clsEmpleado empleado);
    public boolean edit(clsEmpleado empleado);
    public boolean eliminar(int idEmpleado);
    public boolean iniciarSesion(clsEmpleado empleado);
    public clsEmpleado buscarPorDni(String dni);
    public String buscarNombreCargo(clsEmpleado empleado);
}
