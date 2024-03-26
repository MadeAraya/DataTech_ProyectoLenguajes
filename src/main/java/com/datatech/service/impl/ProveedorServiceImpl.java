package com.datatech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datatech.dao.ProveedorDao;
import com.datatech.domain.Proveedor;
import com.datatech.service.ProveedorService;




@Service
public class ProveedorServiceImpl implements ProveedorService{
    @Autowired
    private ProveedorDao proveedorDao;


    @Override
    public List<Proveedor> getProveedores() {
        var lista = proveedorDao.findAll();
        return lista;
    }

}
