package br.com.upmasters.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ITEM_PEDIDO")
public class ItemPedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "pedido_id")
  private Pedido pedido;
  @ManyToOne
  @JoinColumn(name = "produto_id")
  private Produto produto;
  private Integer quantidade;

}