package com.upmasters.localizacao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

  @Id
  @Column(name = "id_cidade", length = 50)
  private Long Id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "qtde_habitantes")
  private Long habitantes;

}