package br.com.upmasters.rest.controller;

import br.com.upmasters.domain.entity.Cliente;
import br.com.upmasters.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

  @Autowired
  private ClientesRepository clientesRepository;

  @GetMapping("{id}")
  public Cliente getCliente(@PathVariable Integer id) {

    Optional<Cliente> cliente = clientesRepository.findById(id);

    if (cliente.isPresent())
      return cliente.get();

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente saveCliente(@RequestBody Cliente cliente) {
    Cliente clienteSalvo = clientesRepository.save(cliente);
    return clienteSalvo;
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCliente(@PathVariable Integer id) {
    Optional<Cliente> cliente = clientesRepository.findById(id);

    if (!cliente.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");

    clientesRepository.delete(cliente.get());
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizarCliente(@PathVariable Integer id
      , @RequestBody Cliente cliente) {

    clientesRepository
        .findById(id)
        .map(c -> {
          cliente.setId(c.getId());
          clientesRepository.save(cliente);
          return c;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
            , "Cliente não encontrado"));
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

    List<Cliente> listaClientes = clientesRepository.findAll(example);

    return listaClientes;
  }

}
