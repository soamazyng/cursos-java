package br.com.upmasters.rest.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoStatusPedidoDTO {

  @JsonProperty("novo_status")
  private String novoStatus;
}