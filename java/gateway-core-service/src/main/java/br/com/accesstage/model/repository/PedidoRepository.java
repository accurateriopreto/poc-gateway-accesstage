package br.com.accesstage.model.repository;

import br.com.accesstage.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 *
 * @author caio
 */
public interface PedidoRepository extends JpaRepository<Pedido, String>{
    
}
