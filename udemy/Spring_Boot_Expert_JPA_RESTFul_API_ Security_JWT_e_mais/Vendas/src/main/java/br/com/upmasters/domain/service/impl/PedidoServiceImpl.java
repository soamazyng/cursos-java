package br.com.upmasters.domain.service.impl;

import br.com.upmasters.domain.entity.Cliente;
import br.com.upmasters.domain.entity.ItemPedido;
import br.com.upmasters.domain.entity.Pedido;
import br.com.upmasters.domain.entity.Produto;
import br.com.upmasters.domain.enums.StatusPedido;
import br.com.upmasters.domain.repository.ClientesRepository;
import br.com.upmasters.domain.repository.ItensPedidosRepository;
import br.com.upmasters.domain.repository.PedidosRepository;
import br.com.upmasters.domain.repository.ProdutosRepository;
import br.com.upmasters.domain.service.PedidoService;
import br.com.upmasters.exception.NotFoundDataException;
import br.com.upmasters.exception.RegraNegocioException;
import br.com.upmasters.rest.controller.dto.ItemPedidoDto;
import br.com.upmasters.rest.controller.dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

  private final PedidosRepository pedidosRepository;

  private final ClientesRepository clientesRepository;

  private final ProdutosRepository produtosRepository;

  private final ItensPedidosRepository itensPedidosRepository;

  @Override
  @Transactional
  public Pedido salvar(PedidoDto dto) {

    Cliente cliente = clientesRepository.findById(dto.getCliente())
        .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));


    Pedido pedido = new Pedido();
    pedido.setTotal(dto.getTotal());
    pedido.setDataPedido(LocalDateTime.now());
    pedido.setCliente(cliente);
    pedido.setStatus(StatusPedido.REALIZADO);

    List<ItemPedido> itensPedido = salvarItem(pedido, dto.getItems());

    pedidosRepository.save(pedido);

    itensPedidosRepository.saveAll(itensPedido);

    pedido.setItens(itensPedido);

    return pedido;
  }

  @Override
  public Optional<Pedido> obterPedidoCompleto(Integer id) {
    return pedidosRepository.findByIdFetchItens(id);
  }

  @Override
  @Transactional
  public void atualizaStatus(Integer id, StatusPedido statusPedido) {
    pedidosRepository
        .findById(id)
        .map(pedido -> {
          pedido.setStatus(statusPedido);
          return pedidosRepository.save(pedido);
        })
        .orElseThrow(() -> new NotFoundDataException("Pedido não encontrado"));
  }

  private List<ItemPedido> salvarItem(Pedido pedido, List<ItemPedidoDto> items) {

    if (items.isEmpty())
      throw new RegraNegocioException("Não é possível realizar um pedido sem items.");

    return items
        .stream()
        .map(
            item -> {
              Produto produto = produtosRepository
                  .findById(item.getProduto())
                  .orElseThrow(() -> new RegraNegocioException("Código de produto invalido: " + item.getProduto()));

              ItemPedido itemPedido = new ItemPedido();
              itemPedido.setQuantidade(item.getQuantidade());
              itemPedido.setPedido(pedido);
              itemPedido.setProduto(produto);
              return itemPedido;
            }).collect(Collectors.toList());

  }
}