
package com.datatech.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tab_detalle_venta")
public class DetalleVenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private BigDecimal idDetalle;
    private BigDecimal idVenta;
    private BigDecimal idProducto;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;

    public DetalleVenta() {
    }

    public DetalleVenta(BigDecimal idDetalle, BigDecimal idVenta, BigDecimal idProducto, BigDecimal cantidad, BigDecimal precioUnitario) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idProducto = idProducto;
    }
    
    public BigDecimal getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(BigDecimal idDetalle) {
        this.idDetalle = idDetalle;
    }

    public BigDecimal getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(BigDecimal idVenta) {
        this.idVenta = idVenta;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(BigDecimal idProducto) {
        this.idProducto = idProducto;
    }    
    
}
