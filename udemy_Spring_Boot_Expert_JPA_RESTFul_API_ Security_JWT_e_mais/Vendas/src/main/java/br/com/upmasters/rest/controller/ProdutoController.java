package br.com.upmasters.rest.controller;

import br.com.upmasters.domain.entity.Produto;
import br.com.upmasters.domain.repository.ProdutosRepository;
import br.com.upmasters.exception.NotFoundDataException;
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
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

  private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
  private final ProdutosRepository produtosRepository;

  @GetMapping("{id}")
  public Produto getProduto(@PathVariable Integer id) {

    return produtosRepository.findById(id)
        .orElseThrow(() -> new NotFoundDataException(PRODUTO_NAO_ENCONTRADO));

  }

  @PostMapping
  @ResponseStatus(CREATED)
  public Produto saveProduto(@RequestBody @Valid Produto produto) {
    return produtosRepository.save(produto);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(NO_CONTENT)
  public void deleteProduto(@PathVariable Integer id) {

    Optional<Produto> produto = produtosRepository.findById(id);

    if (produto.isEmpty())
      throw new NotFoundDataException(PRODUTO_NAO_ENCONTRADO);

    produtosRepository.delete(produto.get());

  }

  @PutMapping("{id}")
  @ResponseStatus(NO_CONTENT)
  public void updateProduto(@PathVariable Integer id, @RequestBody @Valid Produto produto) {

    Optional<Produto> produtoAtualizado = produtosRepository.findById(id).map(
        p -> {
          produto.setId(p.getId());
          produtosRepository.save(produto);
          return p;
        });

    if (produtoAtualizado.isEmpty())
      throw new NotFoundDataException(PRODUTO_NAO_ENCONTRADO);

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