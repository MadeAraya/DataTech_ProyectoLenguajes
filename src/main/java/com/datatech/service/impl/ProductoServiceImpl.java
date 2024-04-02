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
import com.datatech.dao.ProductoDao;
import com.datatech.domain.Producto;
import com.datatech.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos", Producto.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2, "tab_producto");
        query.execute();
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProductoPorId(Long idProducto) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos_porID", Producto.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.setParameter(2, "tab_producto");
        query.setParameter(3, idProducto);
        query.execute();
        List<Producto> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public void insertarProducto(Long idCategoria, String nombre, String descripcion, double precioUnitario) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_insertar_producto");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, Double.class, ParameterMode.IN);
        query.setParameter(1, idCategoria);
        query.setParameter(2, nombre);
        query.setParameter(3, descripcion);
        query.setParameter(4, precioUnitario);
        query.execute();
    }

    @Override
    @Transactional
    public void actualizarProducto(Long idProducto, String nombre, String descripcion, double precioUnitario,
            Long idCategoria) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_actualizar_producto");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, Double.class, ParameterMode.IN);
        query.setParameter(1, idProducto);
        query.setParameter(2, idCategoria);
        query.setParameter(3, nombre);
        query.setParameter(4, descripcion);
        query.setParameter(5, precioUnitario);
        query.execute();
    }

    @Override
    @Transactional
    public void eliminarProducto(Long idProducto) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_registro");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(1, idProducto);
        query.setParameter(2, "tab_producto");
        query.execute();
    }

}