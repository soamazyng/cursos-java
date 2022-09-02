package br.com.upmasters.domain.service.impl;

import br.com.upmasters.domain.service.PedidoService;
import br.com.upmasters.rest.controller.dto.ItemPedidoDto;
import br.com.upmasters.rest.controller.dto.PedidoDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class PedidoServiceImplTest {

  @Autowired
  private PedidoService pedidoService;

  @Test
  public void TestInsertPedido() {

    ItemPedidoDto itemPedidoDto = new ItemPedidoDto();
    itemPedidoDto.setProduto(1);
    itemPedidoDto.setQuantidade(100);

    List<ItemPedidoDto> itensPedidoDto = new ArrayList<ItemPedidoDto>();
    itensPedidoDto.add(itemPedidoDto);

    PedidoDto pedidoDto = new PedidoDto();
    pedidoDto.setCliente(1);
    pedidoDto.setTotal(BigDecimal.valueOf(100.00));
    pedidoDto.setItems(itensPedidoDto);

    pedidoService.salvar(pedidoDto);

    Assertions.assertThat(pedidoService)
        .isNotNull();

  }

}