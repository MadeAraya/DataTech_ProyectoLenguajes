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