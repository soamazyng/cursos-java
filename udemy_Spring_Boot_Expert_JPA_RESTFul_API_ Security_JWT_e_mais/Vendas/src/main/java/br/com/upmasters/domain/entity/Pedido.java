package br.com.upmasters.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @Column(name = "data_pedido")
  private LocalDateTime dataPedido;

  @Column(precision = 20, scale = 2)
  private BigDecimal total;

  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> itens;

  public List<ItemPedido> getItens() {
    return itens;
  }

  public void setItens(List<ItemPedido> itens) {
    this.itens = itens;
  }

  public Pedido() {

  }

  public Pedido(Integer id, Cliente cliente, LocalDateTime dataPedido, BigDecimal total) {
    this.id = id;
    this.cliente = cliente;
    this.dataPedido = dataPedido;
    this.total = total;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public LocalDateTime getDataPedido() {
    return dataPedido;
  }

  public void setDataPedido(LocalDateTime dataPedido) {
    this.dataPedido = dataPedido;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  @Override
  public String toString() {
    return "Pedido{" +
        "id=" + id +
        ", dataPedido=" + dataPedido +
        ", total=" + total +
        '}';
  }
}
