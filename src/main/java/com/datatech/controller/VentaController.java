/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datatech.controller;

import com.datatech.domain.Venta;
import com.datatech.service.VentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Made Araya
 */
@Controller
public class VentaController {
    
    @Autowired
    private VentaService ventaService;

    @GetMapping("/ventas")
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaService.obtenerVentas();
        model.addAttribute("ventas", ventas);
        return "ventas/ventas";
    }

    @GetMapping("/ventas/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("venta", new Venta());
        return "ventas/form-crear-venta";
    }

    @PostMapping("/ventas/crear")
    public String crearVenta(@ModelAttribute("venta") Venta venta) {
        ventaService.crearVenta(venta);
        return "redirect:/ventas/listar";
    }

    @PostMapping("/ventas/eliminar/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable int id) {
        ventaService.eliminarVenta(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
