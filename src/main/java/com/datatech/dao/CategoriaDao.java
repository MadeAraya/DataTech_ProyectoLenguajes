/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.datatech.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.datatech.domain.Categoria;

/**
 *
 * @author manue
 */
public interface CategoriaDao extends JpaRepository<Categoria,Long> {
    
}

