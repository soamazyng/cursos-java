package br.com.upmasters.rest.controller.dto;

import br.com.upmasters.valitation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

  @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
  private Integer cliente;

  @NotNull(message = "{campo.total-pedido.obrigatorio}")
  private BigDecimal total;

  @NotEmptyList()
  private List<ItemPedidoDto> items;

}