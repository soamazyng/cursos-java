package br.com.upmasters.rest.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteFiltroDTO {

  private Integer id;
  private String nome;
  private String cpf;

}