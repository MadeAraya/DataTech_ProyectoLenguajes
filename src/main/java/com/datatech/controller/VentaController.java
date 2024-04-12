/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datatech.controller;

import com.datatech.domain.Cliente;
import com.datatech.domain.Sucursal;
import com.datatech.domain.Venta;
import com.datatech.service.ClienteService;
import com.datatech.service.SucursalService;
import com.datatech.service.VentaService;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Made Araya
 */
@Controller
public class VentaController {
    
    @Autowired
    private VentaService ventaService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private SucursalService sucursalService;
    
    @GetMapping("/ventas")
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaService.obtenerVentas();
        model.addAttribute("ventas", ventas);
        List<Cliente> clientes = clienteService.obtenerClientes();
        model.addAttribute("clientes", clientes);
        List<Sucursal> sucursales = sucursalService.getSucursales();
        model.addAttribute("sucursales", sucursales);
        return "ventas/ventas";
    }
    
    @GetMapping("/ventas/agregar")
    public String mostrarFormularioagregar(Model model) {
        List<Cliente> clientes = clienteService.obtenerClientes();
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clientes);
        List<Sucursal> sucursales = sucursalService.getSucursales();
        model.addAttribute("sucursales", sucursales);
        return "ventas/agregarVenta";
    }

    @PostMapping("/venta/crear")
    public String insertarVenta(
            @RequestParam("idCliente") long idCliente,
            @RequestParam("idSucursal") long idSucursal,
            @RequestParam("totalPagado") long totalPagado,
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date fecha) {
        Date fechaSQL = new Date(fecha.getTime());
        ventaService.insertarVenta(idCliente, idSucursal, totalPagado, fechaSQL);
        return "redirect:/ventas";
    }

}
