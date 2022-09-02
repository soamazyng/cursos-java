package br.com.upmasters.rest.controller;

import br.com.upmasters.domain.entity.Cliente;
import br.com.upmasters.domain.repository.ClientesRepository;
import br.com.upmasters.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

  private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
  private final ClientesRepository clientesRepository;

  @GetMapping("{id}")
  public Cliente getCliente(@PathVariable Integer id) {

    Optional<Cliente> cliente = clientesRepository.findById(id);

    if (cliente.isPresent())
      return cliente.get();

    throw new NotFoundDataException(CLIENTE_NAO_ENCONTRADO);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public Cliente saveCliente(@RequestBody Cliente cliente) {
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
      , @RequestBody Cliente cliente) {

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
  public List<Cliente> findCliente(Cliente filtro) {

    // utilizado para validar todos os atributos enviados
    // e verifica se estão preenchidos ou não
    ExampleMatcher matcher = ExampleMatcher
        .matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    // depois de verificar cada um dos atributos é feito um match
    // e enviado para o findall retornando o que foi encontrado
    Example<Cliente> example = Example.of(filtro, matcher);

    return clientesRepository.findAll(example);

  }

}
