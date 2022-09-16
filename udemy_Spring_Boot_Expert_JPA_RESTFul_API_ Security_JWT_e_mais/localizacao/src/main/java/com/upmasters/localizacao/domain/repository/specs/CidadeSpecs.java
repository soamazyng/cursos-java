package com.upmasters.localizacao.domain.repository.specs;

import com.upmasters.localizacao.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;
import org.apache.commons.lang3.StringUtils;

public abstract class CidadeSpecs {

  public static Specification<Cidade> propertyEqual(String prop, Object value){
    return (root, query, cb) -> cb.equal( root.get(prop), value );
  }

  public static Specification<Cidade> idEqual(Long id){
    return (root, query, cb) -> cb.equal( root.get("Id"), id );
  }

  public static Specification<Cidade> nomeEqual(String nome){
    return (root, query, cb) -> cb.equal( root.get("nome"), nome );
  }

  public static Specification<Cidade> habitantesGreaterThan(Long habitantes){
    return (root, query, cb) -> cb.greaterThan( root.get("habitantes"), habitantes );
  }
  
  public static Specification<Cidade> habitantesBetween(Long min, Long max){
    return (root, query, cb) -> cb.between(root.get("habitantes"), min, max);
  }

  public static Specification<Cidade> nomeLike(String nome){
    return (root, query, cb) -> cb
        .like(cb.upper(root.get("nome"))
            , StringUtils.upperCase("%" + nome + "%"));
  }

}