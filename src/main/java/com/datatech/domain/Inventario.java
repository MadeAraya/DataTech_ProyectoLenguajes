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

/*
CREATE TABLE tab_inventario(
	id_inventario NUMBER NOT NULL,
	id_sucursal NUMBER NOT NULL,
	id_producto NUMBER NOT NULL,
	id_proveedor NUMBER NOT NULL,
	cant_disponible NUMBER NOT NULL,
    PRIMARY KEY (id_inventario),
    CONSTRAINT FK_sucursal_tab_inventario FOREIGN KEY (id_sucursal) REFERENCES tab_sucursal(id_sucursal),
    CONSTRAINT FK_producto_tab_inventario FOREIGN KEY (id_producto) REFERENCES tab_producto(id_producto),
    CONSTRAINT FK_proveedor_tab_inventario FOREIGN KEY (id_proveedor) REFERENCES tab_proveedor(id_proveedor)
);
*/ 