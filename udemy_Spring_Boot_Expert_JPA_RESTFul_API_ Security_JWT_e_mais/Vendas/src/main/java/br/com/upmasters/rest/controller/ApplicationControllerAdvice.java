package br.com.upmasters.rest.controller;

import br.com.upmasters.exception.ApiErros;
import br.com.upmasters.exception.NotFoundDataException;
import br.com.upmasters.exception.PedidoNaoEncontradoException;
import br.com.upmasters.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(RegraNegocioException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErros handleRegraNegocioException(RegraNegocioException ex) {
    String mensagemErro = ex.getMessage();
    return new ApiErros(mensagemErro);
  }

  @ExceptionHandler(NotFoundDataException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiErros handleNotFoundException(NotFoundDataException ex) {
    String mensagemErro = ex.getMessage();
    return new ApiErros(mensagemErro);
  }

  @ExceptionHandler(PedidoNaoEncontradoException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiErros handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
    String mensagemErro = ex.getMessage();
    return new ApiErros(mensagemErro);
  }

}