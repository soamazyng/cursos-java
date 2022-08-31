package br.com.upmasters.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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


  @Column(length = 11)
  private String cpf;

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  @JsonIgnore
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
