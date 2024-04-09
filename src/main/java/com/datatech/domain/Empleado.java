/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datatech.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "tab_empleado")
public class Empleado implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id_empleado")
  private Long idEmpleado;

  @ManyToOne
  @JoinColumn(name = "id_sucursal")
  private Sucursal sucursal;

  @ManyToOne
  @JoinColumn(name = "id_cargo")
  private Cargo cargo;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date fechaContratacion;

  private String nombre;
  private String apellido;
  private String telefono;
  private String email;

}

/*
 * CREATE TABLE tab_empleado(
 * id_empleado NUMBER NOT NULL,
 * id_sucursal NUMBER NOT NULL,
 * id_cargo NUMBER NOT NULL,
 * fecha_contratacion DATE NOT NULL,
 * nombre varchar2(80) NOT NULL,
 * apellido varchar2(80) NOT NULL,
 * telefono varchar2(80) NOT NULL,
 * email varchar2(80) NOT NULL,
 * PRIMARY KEY (id_empleado),
 * CONSTRAINT fk_sucursal_tab_empleado FOREIGN KEY (id_sucursal) REFERENCES
 * tab_sucursal(id_sucursal),
 * CONSTRAINT fk_cargo_tab_empleado FOREIGN KEY (id_cargo) REFERENCES
 * tab_cargo(id_cargo)
 * );
 */
