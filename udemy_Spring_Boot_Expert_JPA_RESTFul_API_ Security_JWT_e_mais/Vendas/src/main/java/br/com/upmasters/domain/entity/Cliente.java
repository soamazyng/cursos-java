package br.com.upmasters.domain.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;
  @Column(length = 100)
  private String nome;

  @OneToMany(mappedBy = "cliente")
  private Set<Pedido> pedidos;

  public Cliente() {
  }

  public Cliente(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public Set<Pedido> getPedidos() {
    return pedidos;
  }

  public void setPedidos(Set<Pedido> pedidos) {
    this.pedidos = pedidos;
  }

  public Cliente(String nome) {
    this.nome = nome;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return "Cliente{" +
        "id=" + this.getId() +
        ", Nome='" + this.getNome() + '\'' +
        '}';
  }
}
