
package com.datatech.service;

import java.util.List;
import com.datatech.domain.Categoria;

public interface CategoriaService {
    // Se define la firma de un metodo para obtener la lista de registros de la table categoria
    public List<Categoria> getCategorias();
    /*getCategoriaPorId */
    public Categoria getCategoriaPorId(Long idCategoria);

    // Metodos provenientes de CategoriaRepositoryService
    public void insertarCategoria(String nombre);
    void actualizarCategoria(Long idCategoria, String nombre);
    public void eliminarCategoria(Long idCategoria);
}
