package br.com.upmasters.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUsuarioDto {

  public Integer id;
  public String login;
  public boolean isAdmin;

}