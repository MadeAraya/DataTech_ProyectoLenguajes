package com.datatech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datatech.dao.InventarioDao;
import com.datatech.domain.Inventario;
import com.datatech.service.InventarioService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
public class InventarioServiceImpl implements InventarioService {
    @Autowired
    private InventarioDao inventarioDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Inventario> getInventario() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos", Inventario.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2, "tab_inventario");
        query.execute();
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Inventario getInventarioPorId(Long idInventario) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos_porID",
                Inventario.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.setParameter(2, "tab_inventario");
        query.setParameter(3, idInventario);
        query.execute();
        List<Inventario> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public void eliminarInventario(Long idInventario) {
        entityManager.createStoredProcedureQuery("eliminar_registro")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, idInventario)
                .setParameter(2, "tab_inventario")
                .execute();
    }

    @Override
    @Transactional
    public void insertarInventario(Long idSucursal, Long idProducto, Long idProveedor, Long cantDisponible) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("pkg_insertar_datos.sp_insertar_inventario");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN);
        query.setParameter(1, idSucursal);
        query.setParameter(2, idProducto);
        query.setParameter(3, idProveedor);
        query.setParameter(4, cantDisponible);
        query.execute();
    }

    @Override
    @Transactional
    public void actualizarInventario(Long idInventario, Long idSucursal, Long idProducto, Long idProveedor,
            Long cantDisponible) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("pkg_actualizar_datos.sp_actualizar_inventario");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, Long.class, ParameterMode.IN);
        query.setParameter(1, idInventario);
        query.setParameter(2, idSucursal);
        query.setParameter(3, idProducto);
        query.setParameter(4, idProveedor);
        query.setParameter(5, cantDisponible);
        query.execute();
    }

}
