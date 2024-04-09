package com.datatech.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.datatech.service.SucursalService;
import com.datatech.dao.SucursalDao;
import com.datatech.domain.Sucursal;

import javax.persistence.*;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalDao SucursalDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Sucursal> getSucursales() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos", Sucursal.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2, "tab_sucursal");
        query.execute();
        return query.getResultList();

    }

    @Override
    @Transactional(readOnly = true)
    public Sucursal getSucursalPorId(Long idSucursal) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos_porID", Sucursal.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.setParameter(2, "tab_sucursal");
        query.setParameter(3, idSucursal);
        query.execute();
        List<Sucursal> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void crearSucursal(String nombre, String provincia, String canton, String direccion, String telefono,
            String sitioWeb) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_insertar_sucursal");
        query.registerStoredProcedureParameter("v_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_provincia", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_canton", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_direccion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_telefono", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_sitio_web", String.class, ParameterMode.IN);

        query.setParameter("v_nombre", nombre);
        query.setParameter("v_provincia", provincia);
        query.setParameter("v_canton", canton);
        query.setParameter("v_direccion", direccion);
        query.setParameter("v_telefono", telefono);
        query.setParameter("v_sitio_web", sitioWeb);

        query.execute();
    }

    @Override
    @Transactional
    public void actualizarSucursal(Sucursal sucursal) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_actualizar_sucursal");
        query.registerStoredProcedureParameter("v_id_sucursal", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_provincia", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_canton", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_direccion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_telefono", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_sitio_web", String.class, ParameterMode.IN);

        query.setParameter("v_id_sucursal", sucursal.getIdSucursal());
        query.setParameter("v_nombre", sucursal.getNombre());
        query.setParameter("v_provincia", sucursal.getProvincia());
        query.setParameter("v_canton", sucursal.getCanton());
        query.setParameter("v_direccion", sucursal.getDireccion());
        query.setParameter("v_telefono", sucursal.getTelefono());
        query.setParameter("v_sitio_web", sucursal.getSitioWeb());

        query.execute();
    }

    @Override
    @Transactional
    public void eliminarSucursal(Long idSucursal) {
        entityManager.createStoredProcedureQuery("eliminar_registro")
                .registerStoredProcedureParameter("v_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_tabla", String.class, ParameterMode.IN)
                .setParameter("v_id", idSucursal)
                .setParameter("v_tabla", "tab_sucursal")
                .execute();
    }

}
