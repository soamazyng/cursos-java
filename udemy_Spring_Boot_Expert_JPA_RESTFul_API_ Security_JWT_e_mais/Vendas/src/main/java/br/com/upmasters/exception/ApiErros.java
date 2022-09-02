package br.com.upmasters.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

  @Getter
  private List<String> errors;

  public ApiErros() {
    
  }

  public ApiErros(String mensagemErro) {
    this.errors = Arrays.asList(mensagemErro);
  }

}
