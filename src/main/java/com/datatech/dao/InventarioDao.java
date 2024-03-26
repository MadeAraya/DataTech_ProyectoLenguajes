package com.datatech.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datatech.domain.Inventario;

public interface InventarioDao extends JpaRepository<Inventario,Long>{
    
}
