/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datatech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datatech.domain.Proveedor;

import com.datatech.service.ProveedorService;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String proveedores(Model model) {
        var lista = proveedorService.getProveedores();
        model.addAttribute("proveedores", lista);
        model.addAttribute("proveedor", new Proveedor());
        return "/proveedores/listado";
    }

    @PostMapping("/agregar")
    public String agregarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorService.insertarProveedor(proveedor.getNombre(), proveedor.getApellido(), proveedor.getEmail(),
                proveedor.getTelefono());
        return "redirect:/proveedores";
    }

     @PostMapping("/editar")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorService.actualizarProveedor(proveedor.getIdProveedor(),proveedor.getNombre(), proveedor.getApellido(), proveedor.getEmail(),
                proveedor.getTelefono());
        return "redirect:/proveedores";
    }

    @GetMapping("/editar/{id}")
    public String editarProveedor(@PathVariable("id") Long idProveedor, Model model) {
        Proveedor proveedor = proveedorService.getProveedorPorId(idProveedor);
        model.addAttribute("proveedor", proveedor);
        return "/proveedores/editar"; 
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable("id") Long idProveedor) {
        proveedorService.eliminarProveedor(idProveedor);
        return "redirect:/proveedores";
    }
}
