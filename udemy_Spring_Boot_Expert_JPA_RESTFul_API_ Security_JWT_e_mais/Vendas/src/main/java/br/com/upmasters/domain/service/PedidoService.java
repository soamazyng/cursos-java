package br.com.upmasters.domain.service;

import br.com.upmasters.domain.entity.Pedido;
import br.com.upmasters.domain.enums.StatusPedido;
import br.com.upmasters.rest.controller.dto.PedidoDto;

import java.util.Optional;

public interface PedidoService {

  Pedido salvar(PedidoDto dto);

  Optional<Pedido> obterPedidoCompleto(Integer id);

  void atualizaStatus(Integer id, StatusPedido statusPedido);

}