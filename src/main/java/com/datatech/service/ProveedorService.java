package com.datatech.service;

import java.util.List;
import com.datatech.domain.Proveedor;

public interface ProveedorService {
    public List<Proveedor> getProveedores();
    
    public Proveedor getProveedorPorId(Long idProveedor);



    void insertarProveedor(String nombre, String apellido, String telefono, String email);
    void actualizarProveedor(Long idProveedor, String nombre, String apellido, String telefono, String email);
    void eliminarProveedor(Long idProveedor);
}
