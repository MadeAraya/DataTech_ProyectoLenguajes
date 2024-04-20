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
import com.datatech.domain.ComentarioObj;
import com.datatech.service.ComentarioObjService;
import com.datatech.service.ProductoService;
import com.datatech.service.ClienteService;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioObjService comentarioService;
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public String comentarios(Model model){
        var lista = comentarioService.getComentarios();
        var listaProductos = productoService.getProductos();
        var listaClientes = clienteService.obtenerClientes();
        model.addAttribute("comentarioObjs",lista);
        model.addAttribute("productos",listaProductos);
        model.addAttribute("clientes",listaClientes);
        model.addAttribute("comentarioObj",new ComentarioObj());
        return "comentarios/listado";
    }
    
    @PostMapping("/agregar")
    public String agregarComentario(@ModelAttribute ComentarioObj comentario) {
        comentarioService.crearComentario(comentario.getIdCliente().getIdCliente(), comentario.getIdProducto().getIdProducto(),
                comentario.getCalificacion(), comentario.getComentario(),comentario.getFecha());
        return "redirect:/comentarios";
    }

    @GetMapping("/editar/{id}")
    public String editarComentario(@PathVariable("id") Long idComentario, Model model) {
        var comentarioObj = comentarioService.getComentarioPorId(idComentario);
        var listaProductos = productoService.getProductos();
        var listaClientes = clienteService.obtenerClientes();
        model.addAttribute("comentarioObj", comentarioObj);
        model.addAttribute("productos", listaProductos);
        model.addAttribute("clientes", listaClientes);
        return "comentarios/editar";
    }
    
    @PostMapping("/editar")
    public String actualizarComentario(@ModelAttribute ComentarioObj comentario) {
        comentarioService.actualizarComentario(comentario.getIdComentario(), comentario.getIdCliente().getIdCliente(), comentario.getIdProducto().getIdProducto(), 
                comentario.getCalificacion(), comentario.getComentario(),comentario.getFecha());
        return "redirect:/comentarios";
    }

    
    @GetMapping("/eliminar/{id}")
    public String eliminarComentario(@PathVariable("id") Long idComentario) {
        comentarioService.eliminarComentario(idComentario);
        return "redirect:/comentarios";
    }
}
