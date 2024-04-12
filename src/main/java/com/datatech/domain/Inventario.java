package com.datatech.domain;

import java.io.Serializable;

import javax.persistence.*;


import lombok.Data;

@Data
@Entity
@Table(name = "tab_inventario")
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L; 

    @Id
    @Column(name = "id_inventario")
    private Long idInventario;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    private Long cantDisponible;

}