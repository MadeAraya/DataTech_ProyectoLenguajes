package com.datatech.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.datatech.service.CategoriaService;
import com.datatech.dao.CategoriaDao;
import com.datatech.domain.Categoria;

import javax.persistence.*;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos", Categoria.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2, "tab_categoria");
        query.execute();
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoriaPorId(Long idCategoria) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos_porID",
                Categoria.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.setParameter(2, "tab_categoria");
        query.setParameter(3, idCategoria);
        query.execute();
        List<Categoria> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public void insertarCategoria(String nombre) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_insertar_categoria");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.setParameter(1, nombre);
        query.execute();
    }

    @Override
    @Transactional
    public void actualizarCategoria(Long idCategoria, String nombre) {
        // Print en consola de id y nombre
        System.out.println("idCategoria: " + idCategoria + " nombre: " + nombre);
        entityManager.createNativeQuery("CALL sp_actualizar_categoria(:idCategoria, :nombre)")
                .setParameter("idCategoria", idCategoria)
                .setParameter("nombre", nombre)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void eliminarCategoria(Long idCategoria) {
        entityManager.createStoredProcedureQuery("eliminar_registro")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, idCategoria)
                .setParameter(2, "tab_categoria")
                .execute();
    }
}
