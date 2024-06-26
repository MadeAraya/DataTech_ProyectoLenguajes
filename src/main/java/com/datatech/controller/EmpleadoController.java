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

import com.datatech.domain.Empleado;
import com.datatech.service.EmpleadoService;
import com.datatech.service.SucursalService;
import com.datatech.service.CargoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;
    
    @Autowired
    private SucursalService sucursalService;
    
    @Autowired
    private CargoService cargoService;
    
    @GetMapping
    public String empleados(Model model){
        var lista = empleadoService.getEmpleados();
        var listaSucursales = sucursalService.getSucursales();
        var listaCargos = cargoService.getCargos();
        model.addAttribute("empleados",lista);
        model.addAttribute("sucursales",listaSucursales);
        model.addAttribute("cargos",listaCargos);
        model.addAttribute("empleado",new Empleado());
        return "empleados/listado";
    }
    
    @PostMapping("/agregar")
    public String agregarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoService.crearEmpleado(empleado.getSucursal().getIdSucursal(), empleado.getCargo().getIdCargo(),
                empleado.getFechaContratacion(), empleado.getNombre(), empleado.getApellido(),
                empleado.getTelefono(), empleado.getEmail());
        return "redirect:/empleados";
    }

    @GetMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable("id") Long idEmpleado, Model model) {
        var empleado = empleadoService.getEmpleadoPorId(idEmpleado);
        var listaSucursales = sucursalService.getSucursales();
        var listaCargos = cargoService.getCargos();
        model.addAttribute("empleado", empleado);
        model.addAttribute("sucursales", listaSucursales);
        model.addAttribute("cargos", listaCargos);
        return "empleados/editar";
    }
    
    @PostMapping("/editar")
    public String actualizarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoService.actualizarEmpleado(empleado.getIdEmpleado(), empleado.getSucursal().getIdSucursal(),empleado.getCargo().getIdCargo(),
                empleado.getFechaContratacion(), empleado.getNombre(), empleado.getApellido(),
                empleado.getTelefono(), empleado.getEmail());
        return "redirect:/empleados";
    }

    
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long idEmpleado) {
        empleadoService.eliminarEmpleado(idEmpleado);
        return "redirect:/empleados";
    }
}
