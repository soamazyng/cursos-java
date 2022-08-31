package br.com.upmasters.rest.controller;

import br.com.upmasters.domain.entity.Produto;
import br.com.upmasters.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  public ProdutosRepository produtosRepository;

  @GetMapping("{id}")
  public Produto getProduto(@PathVariable Integer id) {

    return produtosRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Produto saveProduto(@RequestBody Produto produto) {
    return produtosRepository.save(produto);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduto(@PathVariable Integer id) {

    Optional<Produto> produto = produtosRepository.findById(id);

    if (!produto.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrato");

    produtosRepository.delete(produto.get());

  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateProduto(@PathVariable Integer id, @RequestBody Produto produto) {

    produtosRepository.findById(id).map(
        p -> {
          produto.setId(p.getId());
          produtosRepository.save(produto);
          return p;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

  }

  @GetMapping
  public List<Produto> buscarProduto(Produto filtro) {
    ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Example<Produto> example = Example.of(filtro, exampleMatcher);

    return produtosRepository.findAll(example);

  }

}
