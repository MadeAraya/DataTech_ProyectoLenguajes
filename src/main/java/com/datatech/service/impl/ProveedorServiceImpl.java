package com.datatech.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.datatech.dao.ProveedorDao;
import com.datatech.domain.Proveedor;
import com.datatech.service.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorDao proveedorDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> getProveedores() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos", Proveedor.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2, "tab_proveedor");
        query.execute();
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor getProveedorPorId(Long idProveedor) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos_porID",
                Proveedor.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.setParameter(2, "tab_proveedor");
        query.setParameter(3, idProveedor);
        query.execute();
        List<Proveedor> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public void insertarProveedor(String nombre, String apellido, String email, String telefono) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_insertar_proveedor");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.setParameter(1, nombre);
        query.setParameter(2, apellido);
        query.setParameter(3, email);
        query.setParameter(4, telefono);
        query.execute();
    }

    @Override
    @Transactional
    public void actualizarProveedor(Long idProveedor, String nombre, String apellido, String telefono, String email) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_actualizar_proveedor");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        query.setParameter(1, idProveedor);
        query.setParameter(2, nombre);
        query.setParameter(3, apellido);
        query.setParameter(4, telefono);
        query.setParameter(5, email);
        query.execute();
    }

    @Override
    @Transactional
    public void eliminarProveedor(Long idProveedor) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_registro");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(1, idProveedor);
        query.setParameter(2, "tab_proveedor");
        query.execute();
    }
}