package br.com.upmasters.domain.repository;

import br.com.upmasters.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

  List<Cliente> findByNomeContains(String nome);

  @Query(value = "select c from Cliente c where c.nome like %:nome%")
  List<Cliente> encontrarPorNome(@Param("nome") String nome);


}
