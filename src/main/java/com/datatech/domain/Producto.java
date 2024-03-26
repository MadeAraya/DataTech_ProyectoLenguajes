
package com.datatech.domain;


import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "tab_producto")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    private String nombre;
    private String descripcion;
    private double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

}

/*
 * CREATE TABLE tab_producto(
 * id_producto NUMBER NOT NULL,
 * id_categoria NUMBER NOT NULL,
 * nombre varchar2(80) NOT NULL,
 * descripcion varchar2(100) NOT NULL,
 * precio_unitario NUMBER(10, 2) NOT NULL,
 * PRIMARY KEY (id_producto),
 * CONSTRAINT FK_categoria_tab_producto FOREIGN KEY (id_categoria) REFERENCES
 * tab_categoria(id_categoria)
 * );
 */
