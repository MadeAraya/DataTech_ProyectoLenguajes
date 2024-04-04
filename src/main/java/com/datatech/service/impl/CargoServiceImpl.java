package com.datatech.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.datatech.service.CargoService;
import com.datatech.dao.CargoDao;
import com.datatech.domain.Cargo;

import javax.persistence.*;


@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDao CargoDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true) 
    public List<Cargo> getCargos() {
        var lista = CargoDao.findAll();
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Cargo getCargoPorId(Long idCargo) {
        return CargoDao.findById(idCargo).orElse(null);
    }
    
    @Override
    @Transactional
    public void crearCargo(String nombreCargo,String departamento, int salario ) {
      StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_insertar_cargo");
        query.registerStoredProcedureParameter("nombre_cargo", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("departamento", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("salario", Number.class, ParameterMode.IN);
        
        query.setParameter("nombre_cargo", nombreCargo);
        query.setParameter("departamento", departamento);
        query.setParameter("salario", salario);
        
        query.execute();
    }
    
    @Override
    @Transactional
    public void actualizarCargo(Long idCargo, String nombreCargo,String departamento, int salario ) {
        System.out.println("id_cargo: " + idCargo);
        entityManager.createNativeQuery("CALL sp_actualizar_cargo(:id_cargo,:nombre_cargo,:departamento,:salario)")
                .setParameter("id_cargo", idCargo)
                .setParameter("nombre_cargo", nombreCargo)
                .setParameter("departamento", departamento)
                .setParameter("salario", salario)
                .executeUpdate();
    }
    
    @Override
    @Transactional
    public void eliminarCargo(Long idCargo) {
        entityManager.createStoredProcedureQuery("eliminar_registro")
                .registerStoredProcedureParameter("id_cargo", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("Tabla", String.class, ParameterMode.IN)
                .setParameter("id_cargo", idCargo)
                .setParameter("Tabla", "tab_cargo")
                .execute();
    }

    

    

    
}
