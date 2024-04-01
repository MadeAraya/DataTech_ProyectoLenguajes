
package com.datatech.service;

import java.util.List;
import com.datatech.domain.Empleado;
import java.util.Date;


public interface EmpleadoService {
    public List<Empleado> getEmpleados();
    public Empleado getEmpleadoPorId(Long idEmpleado);
    public void crearEmpleado (Long idSucursal, Long idCargo,Date fechaContratacion,
                        String nombre,String apellido, String telefono, String email);
    public void actualizarEmpleado(Long idEmpleado, Long idSucursal, Long idCargo,
    Date fechaContratacion,String nombre,String apellido, String telefono, String email);
    public void eliminarEmpleado(Long idEmpleado);
}
