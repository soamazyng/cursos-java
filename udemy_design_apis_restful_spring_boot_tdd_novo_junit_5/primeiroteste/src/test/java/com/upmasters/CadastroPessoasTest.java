package com.upmasters;

import com.upmasters.exceptions.CadastroVazioException;
import com.upmasters.exceptions.PessoaSemNomeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CadastroPessoasTest {

  @Test
  public void deveCriarCadastroPessoas(){

    // cenário e execução
    CadastroPessoas cadastroPessoas = new CadastroPessoas();

    // verificação
    Assertions.assertThat( cadastroPessoas.getPessoas()).isEmpty();

  }

  @Test
  public void deveAdicionarUmaPessoa(){
    CadastroPessoas cadastroPessoas = new CadastroPessoas();

    Pessoa pessoa = new Pessoa();
    pessoa.setNome("Jaqueline");

    cadastroPessoas.adicionar(pessoa);

    Assertions
        .assertThat(cadastroPessoas.getPessoas())
        .isNotEmpty()
        .hasSize(1)
        .contains(pessoa);
  }

  @Test
  public void naoDeveAdicionarPessoaComNomeVazio(){

    CadastroPessoas cadastroPessoas = new CadastroPessoas();

    Pessoa pessoa = new Pessoa();

    org.junit.jupiter.api.Assertions
        .assertThrows(PessoaSemNomeException.class,
            () -> cadastroPessoas.adicionar(pessoa));

  }

  @Test
  public void deveRemoverUmaPessoa(){

    CadastroPessoas cadastroPessoas = new CadastroPessoas();
    Pessoa pessoa = new Pessoa();
    pessoa.setNome("Jaqueline");
    cadastroPessoas.adicionar(pessoa);

    cadastroPessoas.remover(pessoa);

    Assertions.assertThat(cadastroPessoas.getPessoas())
        .isEmpty();
  }

  @Test
  @DisplayName("Não deve adicionar uma pessoa com nome vazio.")
  public void deveLancarErroAoTentarRemoverPessoaInexistente(){

    CadastroPessoas cadastroPessoas = new CadastroPessoas();
    Pessoa pessoa = new Pessoa();

    org.junit.jupiter.api.Assertions
        .assertThrows(CadastroVazioException.class,
            () -> cadastroPessoas.remover(pessoa));

  }

}