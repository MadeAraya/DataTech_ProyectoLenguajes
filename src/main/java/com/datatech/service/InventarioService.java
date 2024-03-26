package com.datatech.service;


import java.util.List;
import com.datatech.domain.Inventario;


public interface InventarioService {
    public List<Inventario> getInventario();

    // Método para eliminar un producto por su id
    void eliminarProducto(Long idProducto);
}

