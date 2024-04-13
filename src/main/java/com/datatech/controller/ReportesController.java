/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datatech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datatech.domain.Inventario;
import com.datatech.domain.Sucursal;
import com.datatech.service.EmpleadoService;
import com.datatech.service.InventarioService;
import com.datatech.service.SucursalService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String inventarioReport(Model model) {
        List<Sucursal> sucursales = sucursalService.getSucursales();
        // Lista los empleados que trabajan en cada sucursal, junto con su cargo y
        // salario.
        Map<Sucursal, List<Map<String, Object>>> empleadosPorSucursal = new HashMap<>();
        for (Sucursal sucursal : sucursales) {
            List<Map<String, Object>> empleados = new ArrayList<>();
            for (var empleado : sucursal.getEmpleados()) {
                Map<String, Object> empleadoMap = new HashMap<>();
                empleadoMap.put("nombre", empleado.getNombre());
                empleadoMap.put("apellido", empleado.getApellido());
                empleadoMap.put("cargo", empleado.getCargo().getNombreCargo());
                empleadoMap.put("salario", empleado.getCargo().getSalario());
                empleados.add(empleadoMap);
            }
            empleadosPorSucursal.put(sucursal, empleados);

        }
        // Lista el total de salarios pagados por sucursal.
        Map<String, Double> salarioPorSucursal = new HashMap<>();
        for (Sucursal sucursal : sucursales) {
            double sumaSalarios = sucursal.getEmpleados().stream()
                    .mapToDouble(empleado -> empleado.getCargo().getSalario())
                    .sum();
            salarioPorSucursal.put(sucursal.getNombre(), sumaSalarios);
        }

        model.addAttribute("salarioPorSucursal", salarioPorSucursal);

        model.addAttribute("empleadosPorSucursal", empleadosPorSucursal);
        model.addAttribute("sucursales", sucursales);

        return "reportes/reportesMain";
    }

}
