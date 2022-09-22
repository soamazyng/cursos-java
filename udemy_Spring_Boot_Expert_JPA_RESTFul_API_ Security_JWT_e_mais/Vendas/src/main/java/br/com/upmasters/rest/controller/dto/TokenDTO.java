package br.com.upmasters.rest.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {

  private String login;
  private String token;

}