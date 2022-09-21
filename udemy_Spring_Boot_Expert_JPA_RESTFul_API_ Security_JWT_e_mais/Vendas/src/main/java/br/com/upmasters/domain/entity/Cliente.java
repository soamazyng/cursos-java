package br.com.upmasters.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "CLIENTE")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(length = 100)
  @NotEmpty(message = "{campo.nome.obrigatorio}")
  private String nome;

  @Column(length = 11)
  @NotEmpty(message = "{campo.cpf.obrigatorio}")
  @CPF(message = "{campo.cpf.invalido}")
  private String cpf;

  @JsonIgnore
  @OneToMany(mappedBy = "cliente")
  private Set<Pedido> pedidos;

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

}