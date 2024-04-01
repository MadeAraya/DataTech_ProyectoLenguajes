/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datatech.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name= "tab_empleado")
public class Empleado implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_empleado")
    private Long idEmpleado;
    
    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Long idSucursal;
    
    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Long idCargo;
    
    private Date fechaContratacion;
    
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}

/*
CREATE TABLE tab_empleado(
  id_empleado NUMBER NOT NULL,
  id_sucursal NUMBER NOT NULL,
  id_cargo NUMBER NOT NULL,
  fecha_contratacion DATE NOT NULL,
  nombre varchar2(80) NOT NULL,
  apellido varchar2(80) NOT NULL,
  telefono varchar2(80) NOT NULL,
  email varchar2(80) NOT NULL,
  PRIMARY KEY (id_empleado),
  CONSTRAINT fk_sucursal_tab_empleado FOREIGN KEY (id_sucursal) REFERENCES tab_sucursal(id_sucursal),
  CONSTRAINT fk_cargo_tab_empleado FOREIGN KEY (id_cargo) REFERENCES tab_cargo(id_cargo)
);
*/
