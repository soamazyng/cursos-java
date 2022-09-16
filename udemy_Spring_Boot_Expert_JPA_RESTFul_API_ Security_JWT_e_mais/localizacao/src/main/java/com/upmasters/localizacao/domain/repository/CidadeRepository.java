package com.upmasters.localizacao.domain.repository;

import com.upmasters.localizacao.domain.repository.projections.CidadeProjection;
import com.upmasters.localizacao.entity.Cidade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

  @Query(nativeQuery = true, value = "SELECT c.id_cidade as id, c.nome FROM tb_cidade as c where c.nome = :nome")
  List<CidadeProjection> findByNomeSqlNativo(@Param("nome") String nome);

  List<Cidade> findByNome(String nome);

  List<Cidade> findByNomeStartingWith(String nome);

  List<Cidade> findByNomeEndingWith(String nome);

  List<Cidade> findByNomeContains(String nome);

  List<Cidade> findByNomeLike(String nome);

  @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
  List<Cidade> findByNomeLikeCase(String nome, Sort sort);

  @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
  List<Cidade> findByNomeLikeCase(String nome, Pageable pageable);

  List<Cidade> findByHabitantes(Long habitantes);

  // menor que
  List<Cidade> findByHabitantesLessThan(Long habitantes);

  // menor igual que
  List<Cidade> findByHabitantesLessThanEqual(Long habitantes);

  // maior que
  List<Cidade> findByHabitantesGreaterThan(Long habitantes);

// menor que e nome like
  List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);

}