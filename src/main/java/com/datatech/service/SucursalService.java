
package com.datatech.service;

import java.util.List;
import com.datatech.domain.Sucursal;


public interface SucursalService {
    public List<Sucursal> getSucursal();
    public Sucursal getSucursalPorId(Long idSucursal);
    public void crearSucursal (String nombre, String provincia, String canton,
            String direccion, String telefono, String sitioWeb);
    public void actualizarSucursal(Sucursal sucursal);
    public void eliminarSucursal(Long idSucursal);
}
