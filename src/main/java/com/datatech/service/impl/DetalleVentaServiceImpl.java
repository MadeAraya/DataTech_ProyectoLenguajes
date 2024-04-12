
package com.datatech.service.impl;

import com.datatech.dao.DetalleVentaDao;
import com.datatech.domain.DetalleVenta;
import com.datatech.domain.Producto;
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
import org.springframework.transaction.annotation.Transactional;

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
            detalle.setIdDetalle(((BigDecimal) result[0]).longValue());
            detalle.setIdVenta(((BigDecimal) result[1]).longValue());
            long productoId = ((BigDecimal) result[2]).longValue();
            Producto producto = entityManager.find(Producto.class, productoId);
            detalle.setProducto(producto);
            detalle.setCantidad(((BigDecimal) result[3]).longValue());
            detalle.setPrecioUnitario(((BigDecimal) result[4]).longValue());
            detalleventas.add(detalle);
        }
        return detalleventas;
    }

    @Override
    public void insertarDetalleVenta(long idVenta, long idProducto, long cantidad, double precioUnitario) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("pkg_insertar_datos.sp_insertar_detalle_venta");

        storedProcedure.registerStoredProcedureParameter("v_id_venta", Long.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_id_producto", Long.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_cantidad", Long.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_precio_unitario", Double.class, ParameterMode.IN);

        storedProcedure.setParameter("v_id_venta", idVenta);
        storedProcedure.setParameter("v_id_producto", idProducto);
        storedProcedure.setParameter("v_cantidad", cantidad);
        storedProcedure.setParameter("v_precio_unitario", precioUnitario);
        storedProcedure.execute();
    }

    @Override
    @Transactional
    public void eliminarDetalleVenta(long idDetalle) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_registro");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(1, idDetalle);
        query.setParameter(2, "tab_detalle_venta");
        query.execute();
    }
    
    public void actualizarDetalleVenta(long idDetalle, long idVenta, long idProducto, long cantidad, double precioUnitario){
       StoredProcedureQuery query = entityManager.createStoredProcedureQuery("pkg_actualizar_datos.sp_actualizar_detalle_venta");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, Double.class, ParameterMode.IN);
        query.setParameter(1, idDetalle);
        query.setParameter(2, idVenta);
        query.setParameter(3, idProducto);
        query.setParameter(4, cantidad);
        query.setParameter(5, precioUnitario);
        query.execute(); 
    }
}
