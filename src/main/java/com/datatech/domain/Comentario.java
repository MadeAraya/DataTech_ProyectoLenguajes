package com.datatech.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "tab_comentario")
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L; 

    @Id
    @Column(name = "id_comentario")
    private Long idComentario;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto idProducto;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    
    private Long calificacion;
    private String comentario;
     
    

}
/*
*CREATE TABLE tab_comentario(
*id_comentario NUMBER NOT NULL,
*id_cliente NUMBER NOT NULL,
*id_producto NUMBER NOT NULL,
*calificacion NUMBER NOT NULL CHECK (calificacion >= 1 AND calificacion <= 5),
*comentario varchar2(300) NOT NULL,
*fecha date NOT NULL,
*PRIMARY KEY (id_comentario),
*CONSTRAINT FK_cliente_tab_comentario FOREIGN KEY (id_cliente) REFERENCES tab_cliente(id_cliente),
*CONSTRAINT FK_producto_tab_comentario FOREIGN KEY (id_producto) REFERENCES tab_producto(id_producto)
*);
*/