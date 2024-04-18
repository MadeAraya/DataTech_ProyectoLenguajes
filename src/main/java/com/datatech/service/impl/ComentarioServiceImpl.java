package com.datatech.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.datatech.service.ComentarioService;
import com.datatech.dao.ComentarioDao;
import com.datatech.domain.Comentario;
import java.sql.Date;

import javax.persistence.*;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioDao ComentarioDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> getComentarios() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos", Comentario.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2, "tab_comentario");
        query.execute();
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Comentario getComentarioPorId(Long idComentario) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos_porID", Comentario.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.setParameter(2, "tab_comentario");
        query.setParameter(3, idComentario);
        query.execute();
        List<Comentario> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public void crearComentario(Long idCliente, Long idProducto, Long calificacion,
            String comentario,java.util.Date fecha ) {
        entityManager.createNativeQuery(
                "CALL pkg_insertar_datos.sp_insertar_comentario(:v_id_cliente, :v_id_producto, :v_calificacion, :v_comentario, :v_fecha)")
                .setParameter("v_id_cliente", idCliente)
                .setParameter("v_id_producto", idProducto)
                .setParameter("v_calificacion", calificacion)
                .setParameter("v_comentario", comentario)
                .setParameter("v_fecha", fecha)
                .executeUpdate();
    }

    
    @Override
    @Transactional
    public void actualizarComentario(Long idComentario, Long idCliente, Long idProducto,
            Long calificacion, String comentario, java.util.Date fecha) {
        entityManager.createNativeQuery(
                "CALL pkg_actualizar_datos.sp_actualizar_comentario(:v_id_comentario, :v_id_cliente, :v_id_producto, :v_calificacion, :v_comentario, :v_fecha)")
                .setParameter("v_id_comentario", idComentario)
                .setParameter("v_id_cliente", idCliente)
                .setParameter("v_id_producto", idProducto)
                .setParameter("v_calificacion", calificacion)
                .setParameter("v_comentario", comentario)
                .setParameter("v_fecha", fecha)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void eliminarComentario(Long idComentario) {
        entityManager.createStoredProcedureQuery("eliminar_registro")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, idComentario)
                .setParameter(2, "tab_comentario")
                .execute();
    }
    
}
