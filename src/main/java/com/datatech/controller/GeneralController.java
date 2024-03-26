/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datatech.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.datatech.domain.Categoria;
// import com.datatech.service.CategoriaService;

// @Controller
// @RequestMapping("/")
public class GeneralController {
    // @GetMapping("/")
    // public String index(Model model) {
    //     return "/index";
    // }

    //  @Autowired
    // private CategoriaService categoriaService;

    // //Categoria controladores
    // @GetMapping("/categorias")
    // public String categorias(Model model) {
    //     var lista = categoriaService.getCategorias();
    //     model.addAttribute("categorias", lista);
    //     model.addAttribute("categoria", new Categoria());
    //     return "/categorias/listado";
    // }
    // @PostMapping("/categorias/agregar")
    // public String agregarCategoria(@ModelAttribute Categoria categoria) {
    //     categoriaService.insertarCategoria(categoria.getNombre());//se inserta la categoria con el procedimiento almacenado
    //     return "redirect:/categorias";
    // }

    // @PostMapping("/categorias/editar")
    // public String editarCategoria(@ModelAttribute Categoria categoria) {
    //     categoriaService.actualizarCategoria(categoria.getIdCategoria(), categoria.getNombre());//se actualiza la categoria con el procedimiento almacenado
    //     return "redirect:/categorias";
    // }

    // @GetMapping("/categorias/eliminar/{id}")
    // public String eliminarCategoria(@PathVariable("id") Long idCategoria) {
    //     categoriaService.eliminarCategoria(idCategoria);//se elimina la categoria con el procedimiento almacenado
    //     return "redirect:/categorias";
    // }

}
