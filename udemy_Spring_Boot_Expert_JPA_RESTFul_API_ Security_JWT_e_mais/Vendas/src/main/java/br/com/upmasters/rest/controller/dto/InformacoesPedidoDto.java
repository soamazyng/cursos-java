package br.com.upmasters.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacoesPedidoDto {

  private Integer codigo;
  private String cpf;
  private String nomeCliente;
  private BigDecimal total;
  private List<InformacaoItemPedidoDto> itens;
  private String dataPedido;

  private String status;

}