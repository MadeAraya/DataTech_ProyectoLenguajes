package com.datatech.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.datatech.service.EmpleadoService;
import com.datatech.dao.EmpleadoDao;
import com.datatech.domain.Empleado;
import java.sql.Date;

import javax.persistence.*;


@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoDao EmpleadoDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true) 
    public List<Empleado> getEmpleados() {
        var lista = EmpleadoDao.findAll();
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Empleado getEmpleadoPorId(Long idEmpleado) {
        return EmpleadoDao.findById(idEmpleado).orElse(null);
    }
    
    @Override
    @Transactional
    public void crearEmpleado(Long idSucursal, Long idCargo, java.util.Date fechaContratacion, String nombre, String apellido, String telefono, String email) {
      StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_insertar_empleado");
        query.registerStoredProcedureParameter("v_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_apellido", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_email" , String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_telefono" , String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_fecha_contratacion" , Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_id_sucursal" , Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_id_cargo", Long.class, ParameterMode.IN);
        
        query.setParameter("v_nombre", nombre);
        query.setParameter("v_apellido", apellido);
        query.setParameter("v_email", email);
        query.setParameter("v_telefono", telefono);
         java.sql.Date fechaSql = new java.sql.Date(fechaContratacion.getTime());
        query.setParameter("v_fecha_contratacion", fechaSql);
        query.setParameter("v_id_sucursal", idSucursal);
        query.setParameter("v_id_cargo", idCargo);
        
        query.execute();
    }
    
    @Override
    @Transactional
    public void actualizarEmpleado(Long idEmpleado, Long idSucursal, Long idCargo, java.util.Date fechaContratacion, String nombre, String apellido, String telefono, String email) {
        System.out.println("v_id_empleado: " + idEmpleado);
        entityManager.createNativeQuery("CALL sp_actualizar_empleado(:v_id_empleado,:v_nombre, :v_apellido,"
                + " :v_email, :v_telefono, :v_fecha_contratacion, :v_id_sucursal, :v_id_cargo)")
                .setParameter("v_id_empleado", idEmpleado)
                .setParameter("v_nombre", nombre)
                .setParameter("v_apellido", apellido)
                .setParameter("v_email", email)
                .setParameter("v_telefono", telefono)
                .setParameter("v_fecha_contratacion", fechaContratacion)
                .setParameter("v_id_sucursal", idSucursal)
                .setParameter("v_id_cargo", idCargo)
                .executeUpdate();
    }
    
    @Override
    @Transactional
    public void eliminarEmpleado(Long idEmpleado) {
        entityManager.createStoredProcedureQuery("eliminar_registro")
                .registerStoredProcedureParameter("v_id_empleado", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("Tabla", String.class, ParameterMode.IN)
                .setParameter("v_id_empleado", idEmpleado)
                .setParameter("Tabla", "tab_empleado")
                .execute();
    }

    

    

    
}
