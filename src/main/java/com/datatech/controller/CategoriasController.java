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

import com.datatech.domain.Categoria;
import com.datatech.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
    
    @Autowired
    private CategoriaService categoriaService;

    // Controlador para mostrar la lista de categorías
    @GetMapping
    public String categorias(Model model) {
        var lista = categoriaService.getCategorias();
        model.addAttribute("categorias", lista);
        model.addAttribute("categoria", new Categoria());
        return "categorias/listado";
    }

    // Controlador para agregar una nueva categoría
    @PostMapping("/agregar")
    public String agregarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.insertarCategoria(categoria.getNombre());
        return "redirect:/categorias";
    }

    // Controlador para editar una categoría existente
    @PostMapping("/editar")
    public String editarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.actualizarCategoria(categoria.getIdCategoria(), categoria.getNombre());
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable("id") Long idCategoria, Model model) {
        Categoria categoria = categoriaService.getCategoriaPorId(idCategoria);
        model.addAttribute("categoria", categoria);
        return "categorias/editar";
    }


    // Controlador para eliminar una categoría
    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long idCategoria) {
        categoriaService.eliminarCategoria(idCategoria);
        return "redirect:/categorias";
    }
}
