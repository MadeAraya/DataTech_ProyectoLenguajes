package com.datatech.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="tab_sucursal")
public class Sucursal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_sucursal")
    private Long idSucursal;

    private String nombre;
    private String provincia;
    private String canton;
    private String direccion;
    private String telefono;
    private String sitioWeb;

    @OneToMany(mappedBy = "sucursal")
    private List<Inventario> inventarios;
    
    @OneToMany(mappedBy = "sucursal")
    private List<Empleado> empleados;
    
    
}

/*
CREATE TABLE tab_sucursal (
    id_sucursal NUMBER NOT NULL,
    nombre varchar2(80) NOT NULL,
    provincia varchar2(30) NOT NULL,
    canton varchar2(30) NOT NULL,
    direccion varchar2(100) NOT NULL,
    telefono varchar2(30) NOT NULL,
    sitio_web varchar2(100) NOT NULL,
    PRIMARY KEY (id_sucursal)
    );
 */