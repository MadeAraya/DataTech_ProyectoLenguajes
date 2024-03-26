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

import com.datatech.domain.Proveedor;

import com.datatech.service.ProveedorService;

/**
 *
 * @author manue
 */

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String productos(Model model) {
        var lista = proveedorService.getProveedores();
        model.addAttribute("proveedores", lista);
        model.addAttribute("proveedor", new Proveedor());
        return "/proveedores/listado";
    }

}
