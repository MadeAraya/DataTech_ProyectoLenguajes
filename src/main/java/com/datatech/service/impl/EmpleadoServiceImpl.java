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
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos", Empleado.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2, "tab_empleado");
        query.execute();
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado getEmpleadoPorId(Long idEmpleado) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_obtener_datos_porID", Empleado.class);
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.setParameter(2, "tab_empleado");
        query.setParameter(3, idEmpleado);
        query.execute();
        List<Empleado> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public void crearEmpleado(Long idSucursal, Long idCargo, java.util.Date fechaContratacion, String nombre,
            String apellido, String telefono, String email) {
        entityManager.createNativeQuery(
                "CALL sp_insertar_empleado(:v_nombre, :v_apellido, :v_email, :v_telefono, :v_fecha_contratacion, :v_id_sucursal, :v_id_cargo)")
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
    public void actualizarEmpleado(Long idEmpleado, Long idSucursal, Long idCargo, java.util.Date fechaContratacion,
            String nombre, String apellido, String telefono, String email) {
        entityManager.createNativeQuery(
                "CALL sp_actualizar_empleado(:v_id_empleado, :v_nombre, :v_apellido, :v_email, :v_telefono, :v_fecha_contratacion, :v_id_sucursal, :v_id_cargo)")
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
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, idEmpleado)
                .setParameter(2, "tab_empleado")
                .execute();
    }
    
}
