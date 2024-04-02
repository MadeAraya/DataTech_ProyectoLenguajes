package com.datatech.service.impl;

import com.datatech.dao.ClienteDao;
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

    private ClienteDao clienteDao;
    private final ClienteDao clienteRepository;
    private final EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClienteServiceImpl(ClienteDao clienteDao, ClienteDao clienteRepository, EntityManager entityManager) {
        this.clienteDao = clienteDao;
        this.clienteRepository = clienteRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Cliente> obtenerClientes() { //Me falta obtener los datos por medio de un sp
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
    public Cliente obtenerClientePorId(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_actualizar_cliente");

        storedProcedure.registerStoredProcedureParameter("v_id_cliente", Long.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_nombre", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_apellido1", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_apellido2", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_email", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_telefono", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_fecha_registro", Date.class, ParameterMode.IN);

        storedProcedure.setParameter("v_id_cliente", cliente.getIdCliente());
        storedProcedure.setParameter("v_nombre", cliente.getNombre());
        storedProcedure.setParameter("v_apellido1", cliente.getApellido1());
        storedProcedure.setParameter("v_apellido2", cliente.getApellido2());
        storedProcedure.setParameter("v_email", cliente.getEmail());
        storedProcedure.setParameter("v_telefono", cliente.getTelefono());
        storedProcedure.setParameter("v_fecha_registro", cliente.getFechaRegistro());

        storedProcedure.execute();
    }

    public void eliminarCliente(Long idCliente, String tabla) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("eliminar_registro");

        storedProcedure.registerStoredProcedureParameter("v_id", Long.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_tabla", String.class, ParameterMode.IN);

        storedProcedure.setParameter("v_id", idCliente);
        storedProcedure.setParameter("v_tabla", tabla);

        storedProcedure.execute();
    }

}
