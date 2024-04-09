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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datatech.domain.Inventario;
import com.datatech.domain.Producto;
import com.datatech.service.CategoriaService;
import com.datatech.service.InventarioService;
import com.datatech.service.ProductoService;
import com.datatech.service.ProveedorService;
import com.datatech.service.SucursalService;



/**
 *
 * @author manue
 */

@Controller
@RequestMapping("/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;
    

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProductoService productoService;


    @GetMapping
    public String inventarios(Model model) {
        var lista = inventarioService.getInventario();
        var listaCategorias = categoriaService.getCategorias();
        var listaSucursales = sucursalService.getSucursales();
        var listaProveedores = proveedorService.getProveedores();
        var listaProductos = productoService.getProductos();
        model.addAttribute("inventarios", lista);
        model.addAttribute("categorias", listaCategorias);
        model.addAttribute("sucursales", listaSucursales);
        model.addAttribute("proveedores", listaProveedores);
        model.addAttribute("productos", listaProductos);

        model.addAttribute("inventario", new Inventario());
        
        return "/inventarios/listado";
    }

    @PostMapping("/agregar")
    public String guardar(Inventario inventario) {
        //se valida si el id de la sucursal y id de producto ademas de id proveedoor existen antes de agregar el inventario
        var listaInventarios = inventarioService.getInventario();

        for (Inventario inv : listaInventarios) {
            if (inv.getSucursal().getIdSucursal() == inventario.getSucursal().getIdSucursal() && inv.getProducto().getIdProducto() == inventario.getProducto().getIdProducto()&& inv.getProveedor().getIdProveedor() == inventario.getProveedor().getIdProveedor()){
                return "redirect:/inventarios";
            }
        }
        inventarioService.insertarInventario(inventario.getSucursal().getIdSucursal(), inventario.getProducto().getIdProducto(), inventario.getProveedor().getIdProveedor(), inventario.getCantDisponible());
        
        return "redirect:/inventarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long idInventario) {
        inventarioService.eliminarInventario(idInventario);
        return "redirect:/inventarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long idInventario, Model model) {
        var inventario = inventarioService.getInventarioPorId(idInventario);
        var listaCategorias = categoriaService.getCategorias();
        var listaSucursales = sucursalService.getSucursales();
        var listaProveedores = proveedorService.getProveedores();
        var listaProductos = productoService.getProductos();
        model.addAttribute("inventario", inventario);
        model.addAttribute("categorias", listaCategorias);
        model.addAttribute("sucursales", listaSucursales);
        model.addAttribute("proveedores", listaProveedores);
        model.addAttribute("productos", listaProductos);

        
        return "/inventarios/editar";
    }

    @PostMapping("/editar")
    public String actualizar(Inventario inventario) {
        inventarioService.actualizarInventario(inventario.getIdInventario(), inventario.getSucursal().getIdSucursal(), inventario.getProducto().getIdProducto(), inventario.getProveedor().getIdProveedor(), inventario.getCantDisponible());
        return "redirect:/inventarios";
    }


}
