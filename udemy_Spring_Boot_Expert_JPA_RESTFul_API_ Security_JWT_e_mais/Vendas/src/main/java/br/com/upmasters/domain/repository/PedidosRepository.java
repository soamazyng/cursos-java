package br.com.upmasters.domain.repository;

import br.com.upmasters.domain.entity.Cliente;
import br.com.upmasters.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

  List<Pedido> findByCliente(Cliente cliente);

}
