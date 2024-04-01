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

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
    
    
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