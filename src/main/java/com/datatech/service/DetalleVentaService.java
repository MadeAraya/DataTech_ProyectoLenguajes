/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.datatech.service;
import com.datatech.domain.DetalleVenta;
import java.util.List;

/**
 *
 * @author Genn
 */
public interface DetalleVentaService {
    List<DetalleVenta> obtenerDetalleVentas();
    void crearDetalleVenta(DetalleVenta detalleVenta);
    void eliminarDetalleVenta(int idDetalle);
}
