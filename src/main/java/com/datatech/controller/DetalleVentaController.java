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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/agregarProductoDV")
    public String listarDetalleVentas3(@RequestParam(value = "id", required = false) BigDecimal idVenta, Model model) {
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
        return "detalleVentas/agregarProductoDetalleVentas";
    }

    @PostMapping("/detalleVenta/producto/guardar")
    public String insertarDetalleVenta(
            @RequestParam("idVenta") String idVenta,
            @RequestParam("idProducto") String idProducto,
            @RequestParam("cantidad") long cantidad) {

        long idVentaLong = Long.parseLong(idVenta);
        long idProductoLong = Long.parseLong(idProducto);
        Producto producto = productoService.getProductoPorId(idProductoLong);
        double precioUnitario = producto.getPrecioUnitario();

        detalleVentaService.insertarDetalleVenta(idVentaLong, idProductoLong, cantidad, precioUnitario);
        ventaService.actualizarTotal(idVentaLong);
        return "redirect:/editarDetalleVenta?id=" + idVentaLong;
    }

    @GetMapping("/eliminarDetalle/{id}")
    public String eliminarDetalleVenta(@PathVariable("id") long idDetalle, @RequestParam("idVenta") long idVenta) {
        detalleVentaService.eliminarDetalleVenta(idDetalle);
        ventaService.actualizarTotal(idVenta);
        return "redirect:/editarDetalleVenta?id=" + idVenta;
    }

    @PostMapping("/detalleVenta/producto/actualizar")
    public String actualizarDetalleVenta(
            @RequestParam("idDetalle") String idDetalle,
            @RequestParam("idVenta") String idVenta,
            @RequestParam("idProducto") String idProducto,
            @RequestParam("cantidad") long cantidad,
            @RequestParam("precioUnitario") double precioUnitario) {

        long idDetalleLong = Long.parseLong(idDetalle);
        long idVentaLong = Long.parseLong(idVenta);
        long idProductoLong = Long.parseLong(idProducto);

        detalleVentaService.actualizarDetalleVenta(idDetalleLong, idVentaLong, idProductoLong, cantidad, precioUnitario);
        ventaService.actualizarTotal(idVentaLong);
        return "redirect:/editarDetalleVenta?id=" + idVentaLong;
    }
    
    @GetMapping("/eliminarVenta/{id}")
    public String eliminarVenta(@PathVariable("id") long idVenta) {
        ventaService.eliminarVenta(idVenta);
        return "redirect:/ventas";
    }
    
}
