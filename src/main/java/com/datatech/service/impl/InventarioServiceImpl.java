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

@Service
public class InventarioServiceImpl implements InventarioService {
    @Autowired
    private InventarioDao inventarioDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Inventario> getInventario(){
        var lista = inventarioDao.findAll();
        return lista;
    }

    @Override
    @Transactional
    public void eliminarProducto(Long idProducto) {
        entityManager.createStoredProcedureQuery("eliminar_registro")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, idProducto)
                .setParameter(2, "tab_producto")
                .execute();
    }
    
}
