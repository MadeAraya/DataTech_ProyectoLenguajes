package com.datatech.service;


import java.util.List;
import com.datatech.domain.Producto;
import com.datatech.domain.Proveedor;


public interface ProductoService {
    public List<Producto> getProductos();
    
    public Producto getProductoPorId(Long idProducto);



    void insertarProducto(Long idCategoria, String nombre, String descripcion, double precioUnitario);
    void actualizarProducto(Long idProducto, String nombre, String descripcion, double precioUnitario, Long idCategoria);
    void eliminarProducto(Long idProducto);
}

/*
 * @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    private String nombre;
    private String descripcion;
    private double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

 */