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
    public List<Sucursal> getSucursal() {
        var lista = SucursalDao.findAll();
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Sucursal getSucursalPorId(Long idSucursal) {
        return SucursalDao.findById(idSucursal).orElse(null);
    }
    
    @Override
    public void crearSucursal(String nombre, String provincia, String canton, String direccion, String telefono, String sitioWeb) {
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
    public void actualizarSucursal(Sucursal sucursal){
        System.out.println("v_id_sucursal: " + sucursal.getIdSucursal());
        entityManager.createNativeQuery("CALL sp_actualizar_sucursal(:v_id_sucursal, :v_nombre, :v_provincia,"
                + ":v_canton, :v_direccion, :v_telefono, :v_sitio_web)")
                .setParameter("v_id_sucursal", sucursal.getIdSucursal())
                .setParameter("v_nombre", sucursal.getNombre())
                .setParameter("v_provincia", sucursal.getProvincia())
                .setParameter("v_canton", sucursal.getCanton())
                .setParameter("v_direccion", sucursal.getDireccion())
                .setParameter("v_telefono", sucursal.getTelefono())
                .setParameter("v_sitio_web", sucursal.getSitioWeb())
                .executeUpdate();
        
    }
    
    @Override
    @Transactional
    public void eliminarSucursal(Long idSucursal) {
        entityManager.createStoredProcedureQuery("eliminar_registro")
                .registerStoredProcedureParameter("v_id_sucursal", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("Tabla", String.class, ParameterMode.IN)
                .setParameter("v_id_sucursal", idSucursal)
                .setParameter("Tabla", "tab_sucursal")
                .execute();
    }

    

    

    

    
}
