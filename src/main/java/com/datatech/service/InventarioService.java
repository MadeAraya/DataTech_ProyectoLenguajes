package com.datatech.service;


import java.util.List;
import com.datatech.domain.Inventario;



public interface InventarioService {
    public List<Inventario> getInventario();

    public Inventario getInventarioPorId(Long idInventario);



    void insertarInventario(Long idSucursal, Long idProducto, Long idProveedor, Long cantDisponible);
    
    void actualizarInventario(Long idInventario, Long idSucursal, Long idProducto, Long idProveedor, Long cantDisponible);
    
    void eliminarInventario(Long idInventario);
}

