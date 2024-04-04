/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datatech.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name= "tab_cargo")
public class Cargo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_cargo")
    private Long idCargo;
    
    private String nombreCargo;
    private String departamento;
    private int salario;

    @OneToMany(mappedBy = "cargo")
    private List<Empleado> empleados;
}

/*
CREATE TABLE tab_cargo(   
  id_cargo NUMBER NOT NULL,
  nombre_cargo varchar2(80) NOT NULL,
  departamento varchar2(80) NOT NULL,
  salario NUMBER(10, 2),
  PRIMARY KEY (id_cargo)
);
*/
