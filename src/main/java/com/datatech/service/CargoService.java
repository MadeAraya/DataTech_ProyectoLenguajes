
package com.datatech.service;

import java.util.List;
import com.datatech.domain.Cargo;


public interface CargoService {
    public List<Cargo> getCargos();
    public Cargo getCargoPorId(Long idCargo);
    public void crearCargo (String nombreCargo,String departamento, int salario);
    public void actualizarCargo(Long idCargo, String nombreCargo,String departamento, int salario);
    public void eliminarCargo(Long idCargo);
}
