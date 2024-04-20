
package com.datatech.service;

import java.util.List;
import com.datatech.domain.ComentarioObj;
import java.util.Date;


public interface ComentarioObjService {
    public List<ComentarioObj> getComentarios();
    public ComentarioObj getComentarioPorId(Long idComentario);
    public void crearComentario (Long idCliente, Long idProducto, 
                                  Long calificacion, String comentario, Date fecha);
    public void actualizarComentario(Long idComentario, Long idCliente, Long idProducto, 
                                         Long calificacion, String comentario, Date fecha);
    public void eliminarComentario(Long idComentario);
}
