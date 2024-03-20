package com.datatech.controller;

import com.datatech.domain.Cliente;
import com.datatech.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/listar")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.obtenerClientes();
        model.addAttribute("clientes", clientes);
        return "layout/plantilla";
    }

    @GetMapping("/clientes/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form-crear-cliente";
    }

    @PostMapping("/clientes/crear")
    public String crearCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.crearCliente(cliente);
        return "redirect:/clientes/listar";
    }

    @PostMapping("/clientes/eliminar/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable int id) {
        clienteService.eliminarCliente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
