
package com.datatech.service.impl;

import com.datatech.dao.VentaDao;
import com.datatech.domain.Cliente;
import com.datatech.domain.Sucursal;
import com.datatech.domain.Venta;
import com.datatech.service.VentaService;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
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
public class VentaServiceImpl implements VentaService {
    private final VentaDao ventaDao;
    private final EntityManager entityManager;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public VentaServiceImpl(VentaDao ventaDao, EntityManager entityManager) {
        this.ventaDao = ventaDao;
        this.entityManager = entityManager;
    }

    @Override
    public List<Venta> obtenerVentas() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos");
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2, "tab_venta");
        query.execute();
        List<Object[]> results = query.getResultList();
        List<Venta> ventas = new ArrayList<>();
        for (Object[] result : results) {
            Venta venta = new Venta();
            venta.setIdVenta(((BigDecimal) result[0]).longValue());
            long clienteId = ((BigDecimal) result[1]).longValue();
            Cliente cliente = entityManager.find(Cliente.class, clienteId);
            venta.setCliente(cliente);
            long sucursalId = ((BigDecimal) result[2]).longValue();
            Sucursal sucursal = entityManager.find(Sucursal.class, sucursalId);
            venta.setSucursal(sucursal);
            venta.setTotalPagado(((BigDecimal) result[3]).doubleValue());
            Timestamp timestamp = (Timestamp) result[4];
            LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
            venta.setFecha(Date.valueOf(localDate));
            ventas.add(venta);
        }
        return ventas;
    }

    @Override
    public List<Venta> obtenerVentasPorId(BigDecimal idVenta) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos_porID");
        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, BigDecimal.class, ParameterMode.IN);
        query.setParameter(2, "tab_venta");
        query.setParameter(3, idVenta);
        query.execute();
        List<Object[]> results = query.getResultList();
        List<Venta> ventas = new ArrayList<>();
        for (Object[] result : results) {
            Venta venta = new Venta();
            venta.setIdVenta(((BigDecimal) result[0]).longValue());
            long clienteId = ((BigDecimal) result[1]).longValue();
            Cliente cliente = entityManager.find(Cliente.class, clienteId);
            venta.setCliente(cliente);
            long sucursalId = ((BigDecimal) result[2]).longValue();
            Sucursal sucursal = entityManager.find(Sucursal.class, sucursalId);
            venta.setSucursal(sucursal);
            venta.setTotalPagado(((BigDecimal) result[3]).doubleValue());
            Timestamp timestamp = (Timestamp) result[4];
            LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
            venta.setFecha(Date.valueOf(localDate));
            ventas.add(venta);
        }
        return ventas;
    }

    @Override
    @Transactional
    public void insertarVenta(long idCliente, long idSucursal, double totalPagado, Date fecha) {
        entityManager.createNativeQuery(
                "CALL pkg_insertar_datos.sp_insertar_venta(:v_id_cliente, :v_id_sucursal, :v_total_pagado, :v_fecha)")
                .setParameter("v_id_cliente", idCliente)
                .setParameter("v_id_sucursal", idSucursal)
                .setParameter("v_total_pagado", totalPagado)
                .setParameter("v_fecha", fecha)
                .executeUpdate();
    }

    @Override
    public void eliminarVenta(long idVenta) {
        entityManager.createStoredProcedureQuery("eliminar_registro")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, idVenta)
                .setParameter(2, "tab_venta")
                .execute();
    }
    
    @Override
    public void actualizarTotal(long idVenta) {
        entityManager.createStoredProcedureQuery("SP_ACTUALIZAR_TOTAL_VENTA")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .setParameter(1, idVenta)
                .execute();
    }
}
