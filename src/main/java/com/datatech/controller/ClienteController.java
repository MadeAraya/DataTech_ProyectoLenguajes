package com.datatech.controller;

import com.datatech.domain.Cliente;
import com.datatech.service.ClienteService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/listado") // Muestra el listado de clientes
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.obtenerClientes();
        model.addAttribute("clientes", clientes);
        return "clientes/listado";
    }

    
    @GetMapping("/clientes/crear") // Crear un cliente
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/crear";
    }

    @PostMapping("/clientes/crear") // Accion despues de la creacion 
    public String crearCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.crearCliente(cliente);
        return "redirect:/clientes/listado";
    }

    @GetMapping("/clientes/editar/{id}") // Editar un cliente
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/editar";
    }

    @PostMapping("/clientes/guardar") // Guardar cambios actualizados de cliente
    public String guardarCambiosCliente(@RequestParam("idCliente") Long idCliente,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido1") String apellido1,
            @RequestParam("apellido2") String apellido2,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono,
            @RequestParam("fechaRegistro") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaRegistro) {

        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        cliente.setNombre(nombre);
        cliente.setApellido1(apellido1);
        cliente.setApellido2(apellido2);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);
        cliente.setFechaRegistro(fechaRegistro);

        clienteService.actualizarCliente(cliente);

        return "redirect:/clientes/listado";
    }

    @PostMapping("/clientes/eliminar/{id}") // Eliminar Cliente
    public String eliminarCliente(@PathVariable("id") Long idCliente, @RequestParam("tabla") String tabla) {
        clienteService.eliminarCliente(idCliente, tabla);
        return "redirect:/clientes/listado";
    }

}
