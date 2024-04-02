package com.datatech.service;

import com.datatech.domain.Cliente;
import java.util.List;

public interface ClienteService {

    List<Cliente> obtenerClientes(); //Se usa para obtener el listado NO ES POR SP

    void crearCliente(Cliente cliente); //Se usar para crear el cliente

    Cliente obtenerClientePorId(Long id); //Se usa para obtener el id del cliente por editar
 
    void actualizarCliente(Cliente cliente); //Actualiza los datos editados del cliente

    public void eliminarCliente(Long idCliente, String tabla); //Elimina al cliente por medio del id

}
