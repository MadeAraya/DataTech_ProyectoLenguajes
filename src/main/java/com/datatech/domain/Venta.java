
package com.datatech.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tab_venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private long  idVenta;
    //private BigDecimal  idCliente;
    private double  totalPagado;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;

    
    
    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
