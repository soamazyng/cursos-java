package br.com.upmasters.domain.repository;

import br.com.upmasters.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidosRepository extends JpaRepository<ItemPedido, Integer> {
}
