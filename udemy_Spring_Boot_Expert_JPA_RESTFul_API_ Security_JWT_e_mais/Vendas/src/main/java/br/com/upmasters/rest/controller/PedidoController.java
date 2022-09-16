package br.com.upmasters.rest.controller;

import br.com.upmasters.domain.entity.ItemPedido;
import br.com.upmasters.domain.entity.Pedido;
import br.com.upmasters.domain.enums.StatusPedido;
import br.com.upmasters.domain.service.PedidoService;
import br.com.upmasters.exception.NotFoundDataException;
import br.com.upmasters.rest.controller.dto.AtualizacaoStatusPedidoDTO;
import br.com.upmasters.rest.controller.dto.InformacaoItemPedidoDto;
import br.com.upmasters.rest.controller.dto.InformacoesPedidoDto;
import br.com.upmasters.rest.controller.dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

  private final PedidoService pedidoService;

  @PostMapping
  @ResponseStatus(CREATED)
  public Integer save(@RequestBody @Valid PedidoDto dto) {

    Pedido pedido = pedidoService.salvar(dto);

    return pedido.getId();

  }

  @GetMapping("{id}")
  public InformacoesPedidoDto getById(@PathVariable Integer id) {

    return pedidoService.obterPedidoCompleto(id)
        .map(pedido -> converter(pedido))
        .orElseThrow(() -> new NotFoundDataException("Pedido não encontrado"));
  }

  private InformacoesPedidoDto converter(Pedido pedido) {
    return
        InformacoesPedidoDto.builder()
            .codigo(pedido.getId())
            .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .cpf(pedido.getCliente().getCpf())
            .nomeCliente(pedido.getCliente().getNome())
            .total(pedido.getTotal())
            .status(pedido.getStatus().name())
            .itens(converter(pedido.getItens()))
            .build();
  }

  private List<InformacaoItemPedidoDto> converter(List<ItemPedido> itens) {

    if (CollectionUtils.isEmpty(itens))
      return Collections.emptyList();

    return itens.stream().map(item -> InformacaoItemPedidoDto.builder()
            .descricaoProduto(item.getProduto().getDescricao())
            .quantidade(item.getQuantidade())
            .precoUnitario(item.getProduto().getPreco()).build())
        .collect(Collectors.toList());

  }

  @PatchMapping("{id}")
  @ResponseStatus(NO_CONTENT)
  public void updateStatus(@PathVariable Integer id,
                           @RequestBody @Valid AtualizacaoStatusPedidoDTO atualizacaoStatusPedidoDTO) {

    String novoStatus = atualizacaoStatusPedidoDTO.getNovoStatus();
    pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));

  }
}