package com.datatech.service;

import com.datatech.domain.Cliente;
import java.util.Date;
import java.util.List;

public interface ClienteService {
    List<Cliente> obtenerClientes();

     void crearCliente(Cliente cliente);

     void eliminarCliente(int idCliente);

}


