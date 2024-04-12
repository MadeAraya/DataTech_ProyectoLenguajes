/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.datatech.service;
import com.datatech.domain.Venta;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Genn
 */
public interface VentaService {
    List<Venta> obtenerVentas();
    public List<Venta> obtenerVentasPorId(BigDecimal idVenta);
    void insertarVenta(long idCliente, long idSucursal, double totalPagado, Date fecha);
    void eliminarVenta(long idVenta);
    void actualizarTotal(long idVenta);
    
}
