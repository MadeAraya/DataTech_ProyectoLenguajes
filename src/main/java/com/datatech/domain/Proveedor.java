package com.datatech.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "tab_proveedor")
public class Proveedor implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_proveedor")
    private Long idProveedor;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
}
/*
CREATE TABLE tab_proveedor( 
  id_proveedor NUMBER NOT NULL,
  nombre varchar2(80) NOT NULL,
  apellido varchar2(80) NOT NULL,
  telefono varchar2(80) NOT NULL, 
  email varchar2(80) NOT NULL,
  PRIMARY KEY (id_proveedor)
);
*/ 