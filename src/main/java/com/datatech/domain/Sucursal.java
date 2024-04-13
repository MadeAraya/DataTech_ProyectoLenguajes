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

    @OneToMany(mappedBy = "sucursal")
    private List<Inventario> inventarios;
    
    @OneToMany(mappedBy = "sucursal")
    private List<Empleado> empleados;
   
    @OneToMany(mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<Venta> ventas;
    

    //se itera los inventarios de la sucursal y se suman las cantidades disponibles
    public Long getTotalUnidades() {
        return inventarios.stream()
                          .mapToLong(inventario -> inventario.getCantDisponible())
                          .sum();
    } 

    //se itera la lista de ventas y se retorna la suma del total de ventas
    public Double getTotalVentas() {
        return ventas.stream()
                     .mapToDouble(venta -> venta.getTotalPagado())
                     .sum();
    }

    //se retorna la cantidad de ventas realizadas
    public Long getCantidadVentas() {
        return ventas.stream()
                     .count();
    }
    
}