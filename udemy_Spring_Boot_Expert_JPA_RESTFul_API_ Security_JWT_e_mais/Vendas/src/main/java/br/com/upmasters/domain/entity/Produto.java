package br.com.upmasters.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotEmpty(message = "{campo.descricao.obrigatorio}")
  private String descricao;

  @Column(name = "preco_unitario")
  @NotNull(message = "{campo.preco.obrigatorio}")
  private BigDecimal preco;
}