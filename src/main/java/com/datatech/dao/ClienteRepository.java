package com.datatech.dao;

import com.datatech.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

     void deleteByIdCliente(int idCliente);
}


