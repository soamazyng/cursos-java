package br.com.upmasters.domain.entity;

import br.com.upmasters.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PEDIDO")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @Column(name = "data_pedido")
  private LocalDateTime dataPedido;

  @Column(precision = 20, scale = 2)
  private BigDecimal total;

  @Enumerated(EnumType.STRING)
  private StatusPedido status;

  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> itens;
}