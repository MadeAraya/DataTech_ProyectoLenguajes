
package com.datatech.domain;


import java.io.Serializable;
import java.util.List;

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

    @OneToMany(mappedBy = "producto")
    private List<Inventario> inventarios;

    //metodo q devuelve la cantidad total de stock de los inventarios
    public int getCantidadTotalStock() {
        int cantidadTotal = 0;
        if (inventarios != null) {
            for (Inventario inventario : inventarios) {
                cantidadTotal += inventario.getCantDisponible();
            }
        }
        return cantidadTotal;
    }
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
 * 
 * CREATE TABLE tab_inventario(
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
