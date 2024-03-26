/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datatech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datatech.domain.Inventario;
import com.datatech.domain.Producto;
import com.datatech.service.InventarioService;

/**
 *
 * @author manue
 */

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public String productos(Model model) {
        var lista = inventarioService.getInventario();
        model.addAttribute("inventarios", lista);
        model.addAttribute("inventario", new Inventario()); // Se crea un objeto de tipo Inventario para poder agregar un nuevo producto
        model.addAttribute("producto", new Producto());
        return "/productos/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long idProducto) {
        //imprimir en consola
        System.out.println("idProducto: " + idProducto);
        inventarioService.eliminarProducto(idProducto);
        return "redirect:/productos";
    }


}
