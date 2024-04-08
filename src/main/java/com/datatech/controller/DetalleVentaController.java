
package com.datatech.controller;

import com.datatech.domain.DetalleVenta;
import com.datatech.domain.Producto;
import com.datatech.domain.Venta;
import com.datatech.service.DetalleVentaService;
import com.datatech.service.ProductoService;
import com.datatech.service.VentaService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DetalleVentaController {
    
    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private VentaService ventaService;
    
    @Autowired
    private ProductoService productoService;

    @GetMapping("/detalleVenta")
    public String listarDetalleVentas(@RequestParam(value = "id", required = false) BigDecimal idVenta, Model model) {
        if (idVenta != null) {
            List<DetalleVenta> detalleVentas = detalleVentaService.obtenerDetalleVentas();
            model.addAttribute("detalleVentas", detalleVentas);
            List<Producto> productos = productoService.getProductos();
            model.addAttribute("productos", productos);
            List<Venta> ventas = ventaService.obtenerVentasPorId(idVenta);
            model.addAttribute("ventas", ventas);
        } else {
            System.out.println("HAY UN ERROR***********************************************");
        }
        return "detalleVentas/detalleVentas";
    }
    
        @GetMapping("/editarDetalleVenta")
    public String listarDetalleVentas2(@RequestParam(value = "id", required = false) BigDecimal idVenta, Model model) {
        if (idVenta != null) {
            List<DetalleVenta> detalleVentas = detalleVentaService.obtenerDetalleVentas();
            model.addAttribute("detalleVentas", detalleVentas);
            List<Producto> productos = productoService.getProductos();
            model.addAttribute("productos", productos);
            List<Venta> ventas = ventaService.obtenerVentasPorId(idVenta);
            model.addAttribute("ventas", ventas);
        } else {
            System.out.println("HAY UN ERROR***********************************************");
        }
        return "detalleVentas/modificarDetalleVentas";
    }

}
