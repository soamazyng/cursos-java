package br.com.upmasters.rest.controller;

import br.com.upmasters.domain.entity.Cliente;
import br.com.upmasters.domain.repository.ClientesRepository;
import br.com.upmasters.exception.NotFoundDataException;
import br.com.upmasters.rest.controller.dto.ClienteFiltroDTO;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Api("Api de Clientes")
public class ClienteController {

  private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
  private final ClientesRepository clientesRepository;

  @GetMapping("{id}")
  @ApiOperation("Obter detalhes de um cliente")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Cliente encontrado"),
      @ApiResponse(code = 404, message = "Cliente não encontrado")
  })
  public Cliente getCliente(@PathVariable @ApiParam("Id do cliente") Integer id) {

    Optional<Cliente> cliente = clientesRepository.findById(id);

    if (cliente.isPresent())
      return cliente.get();

    throw new NotFoundDataException(CLIENTE_NAO_ENCONTRADO);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public Cliente saveCliente(@RequestBody @Valid Cliente cliente) {
    return clientesRepository.save(cliente);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(NO_CONTENT)
  public void deleteCliente(@PathVariable Integer id) {
    Optional<Cliente> cliente = clientesRepository.findById(id);

    if (cliente.isEmpty())
      throw new NotFoundDataException(CLIENTE_NAO_ENCONTRADO);

    clientesRepository.delete(cliente.get());
  }

  @PutMapping("{id}")
  @ResponseStatus(NO_CONTENT)
  public void atualizarCliente(@PathVariable Integer id
      , @RequestBody @Valid Cliente cliente) {

    Optional<Cliente> clienteAtualizado = clientesRepository
        .findById(id)
        .map(c -> {
          cliente.setId(c.getId());
          clientesRepository.save(cliente);
          return c;
        });

    if (clienteAtualizado.isEmpty())
      throw new NotFoundDataException(CLIENTE_NAO_ENCONTRADO);

  }

  @GetMapping
  @ApiOperation("Obter detalhes de um cliente")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Cliente encontrado"),
      @ApiResponse(code = 404, message = "Cliente não encontrado")
  })
  public List<Cliente> findCliente(ClienteFiltroDTO filtro) {

    // utilizado para validar todos os atributos enviados
    // e verifica se estão preenchidos ou não
    ExampleMatcher matcher = ExampleMatcher
        .matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Cliente cliente = new Cliente().builder()
        .id(filtro.getId())
        .cpf(filtro.getCpf())
        .nome(filtro.getNome())
        .build();

    // depois de verificar cada um dos atributos é feito um match
    // e enviado para o findall retornando o que foi encontrado
    Example<Cliente> example = Example.of(cliente, matcher);

    return clientesRepository.findAll(example);

  }

}