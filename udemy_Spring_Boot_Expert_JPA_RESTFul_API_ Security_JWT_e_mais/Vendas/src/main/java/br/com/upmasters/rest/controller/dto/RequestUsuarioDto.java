package br.com.upmasters.rest.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUsuarioDto {

  @NotNull(message = "{campo.login.obrigatorio}")
  public String login;

  @NotNull(message = "{campo.senha.obrigatorio}")
  public String senha;

  @JsonProperty("is_admin")
  public boolean isAdmin;

}