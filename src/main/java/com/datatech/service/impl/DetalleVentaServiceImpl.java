
package com.datatech.service.impl;

import com.datatech.dao.DetalleVentaDao;
import com.datatech.domain.DetalleVenta;
import com.datatech.service.DetalleVentaService;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Genn
 */
@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {
    private final DetalleVentaDao detalleVentaDao;
    private final EntityManager entityManager;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public DetalleVentaServiceImpl(DetalleVentaDao detalleVentaDao, EntityManager entityManager) {
        this.detalleVentaDao = detalleVentaDao;
        this.entityManager = entityManager;
    }

    @Override
    public List<DetalleVenta> obtenerDetalleVentas() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos");
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2, "tab_detalle_venta");
        query.execute();
        List<Object[]> results = query.getResultList();
        List<DetalleVenta> detalleventas = new ArrayList<>();
        for (Object[] result : results) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setIdDetalle((BigDecimal) result[0]);
            detalle.setIdVenta((BigDecimal) result[1]);
            detalle.setIdProducto((BigDecimal) result[2]);
            detalle.setCantidad((BigDecimal) result[3]);
            detalle.setPrecioUnitario((BigDecimal) result[4]);
            detalleventas.add(detalle);
        }
        return detalleventas;
    }



    @Override
    public void crearDetalleVenta(DetalleVenta detalleventa) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_insertar_venta");

        storedProcedure.registerStoredProcedureParameter("v_nombre", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_apellido1", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_apellido2", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_email", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_telefono", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_fecha_registro", Date.class, ParameterMode.IN);

        storedProcedure.execute();
    }

    @Override
    public void eliminarDetalleVenta(int idVenta) {
        entityManager.createStoredProcedureQuery("eliminar_registro")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, idVenta)
                .setParameter(2, "tab_venta")
                .execute();
    }
}
