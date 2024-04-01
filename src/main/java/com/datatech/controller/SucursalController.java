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

import com.datatech.domain.Sucursal;
import com.datatech.service.SucursalService;

@Controller
@RequestMapping("/sucursal")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;
    
    @GetMapping
    public String sucursal(Model model){
        var lista = sucursalService.getSucursal();
        model.addAttribute("sucursal",lista);
        model.addAttribute("sucursal",new Sucursal());
        return "sucursal/listado";
    }
    
    @PostMapping("/agregar")
    public String agregarSucursal(@ModelAttribute Sucursal sucursal) {
        sucursalService.crearSucursal( sucursal.getNombre(),
                sucursal.getProvincia(), sucursal.getCanton(), sucursal.getDireccion(),
                sucursal.getTelefono(), sucursal.getSitioWeb());
        return "redirect:/categorias";
    }
    
    @PostMapping("/editar")
    public String actualizarSucursal(@ModelAttribute Sucursal sucursal) {
        sucursalService.actualizarSucursal(sucursal.getIdSucursal(), sucursal.getNombre(),
                sucursal.getProvincia(), sucursal.getCanton(), sucursal.getDireccion(), 
                sucursal.getTelefono(), sucursal.getSitioWeb());
        return "redirect:/sucursal";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarSucursal(@PathVariable("id") Long idSucursal) {
        sucursalService.eliminarSucursal(idSucursal);
        return "redirect:/sucursal";
    }
}
