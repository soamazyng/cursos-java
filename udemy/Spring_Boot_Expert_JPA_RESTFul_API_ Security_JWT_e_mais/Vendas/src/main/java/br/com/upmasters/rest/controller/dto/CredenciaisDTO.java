package br.com.upmasters.rest.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CredenciaisDTO {

  @NotNull(message = "{campo.login.obrigatorio}")
  private String login;

  @NotNull(message = "{campo.senha.obrigatorio}")
  private String senha;

}