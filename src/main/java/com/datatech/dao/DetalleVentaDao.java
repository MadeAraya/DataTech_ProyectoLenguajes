
package com.datatech.dao;

import com.datatech.domain.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Genn
 */
public interface DetalleVentaDao extends JpaRepository<DetalleVenta, Long> {
    
}
