package com.datatech.service.impl;

import com.datatech.dao.ClienteRepository;
import com.datatech.domain.Cliente;
import com.datatech.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final EntityManager entityManager;
        @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, EntityManager entityManager) {
        this.clienteRepository = clienteRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void crearCliente(Cliente cliente) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_insertar_cliente");

        storedProcedure.registerStoredProcedureParameter("v_nombre", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_apellido1", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_apellido2", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_email", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_telefono", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_fecha_registro", Date.class, ParameterMode.IN);

        storedProcedure.setParameter("v_nombre", cliente.getNombre());
        storedProcedure.setParameter("v_apellido1", cliente.getApellido1());
        storedProcedure.setParameter("v_apellido2", cliente.getApellido2());
        storedProcedure.setParameter("v_email", cliente.getEmail());
        storedProcedure.setParameter("v_telefono", cliente.getTelefono());
        java.sql.Date fechaSql = new java.sql.Date(cliente.getFechaRegistro().getTime());
        storedProcedure.setParameter("v_fecha_registro", fechaSql);

        storedProcedure.execute();
    }

    @Override
    public void eliminarCliente(int idCliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
    
}
