package com.upmasters.localizacao.service;

import com.upmasters.localizacao.domain.repository.CidadeRepository;
import com.upmasters.localizacao.entity.Cidade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.upmasters.localizacao.domain.repository.specs.CidadeSpecs.*;

@Service
@RequiredArgsConstructor
public class CidadeService {

  private final CidadeRepository cidadeRepository;

  public void listarCidadesPorNome(){
    cidadeRepository.findByNome("Porto Velho").forEach(System.out::println);
  }

  public void listarCidadesPorNomeSqlNativo(){
    cidadeRepository.findByNomeSqlNativo("Porto Velho")
        .stream().map(cidade -> new Cidade(cidade.getId(), cidade.getNome(), null))
        .forEach(System.out::println);
  }

  public void listarCidadesPorNomeLikeCase(){
    cidadeRepository.findByNomeLikeCase("%porto%",
        Sort.by(Sort.Direction.DESC,"habitantes")).forEach(System.out::println);
  }

  public void listarCidadesPorNomeLikeCasePaging(){

    Pageable pageable = PageRequest.of(0, 2);

    cidadeRepository.findByNomeLikeCase("%a%",
        pageable).forEach(System.out::println);
  }

  public void listarCidadesPorHabitantes(){
    cidadeRepository.findByHabitantes(80000000L).forEach(System.out::println);
  }

  public void listarCidadesPorQtdeHabitantes(){
    cidadeRepository.findByHabitantesLessThan(80000000L).forEach(System.out::println);
  }

  public void listarCidades(){
    cidadeRepository.findAll().forEach(System.out::println);
  }

  public void salvarCidade(){
    var cidade = new Cidade(1L, "São Paulo", 12396372L);
    cidadeRepository.save(cidade);
  }

  public void filtroDinamico(Cidade cidade){

    ExampleMatcher matcher = ExampleMatcher
        .matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

    Example<Cidade> example = Example.of(cidade, matcher);
    cidadeRepository.findAll(example).forEach(System.out::println);

  }

  public void listarCidadesByNomeSpec(Cidade cidade){

    cidadeRepository.findAll(nomeEqual(cidade.getNome())
        .or(habitantesGreaterThan(cidade.getHabitantes())))
                            .forEach(System.out::println);

  }

  public void listarCidadeLikeSpec(Cidade cidade){
    cidadeRepository.findAll(nomeLike(cidade.getNome())).forEach(System.out::println);
  }

  public void listarCidadeSpecFiltroDinamico(Cidade filtro){

    // select * from where 1 = 1
    Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

    if(filtro.getId() != null){
      specs = specs.and(idEqual(filtro.getId()));
    }

    if(StringUtils.hasText(filtro.getNome())){
      specs = specs.and(nomeLike(filtro.getNome()));
    }

    if(filtro.getHabitantes() != null){
      specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
    }

    cidadeRepository.findAll(specs).forEach(System.out::println);

  }

}