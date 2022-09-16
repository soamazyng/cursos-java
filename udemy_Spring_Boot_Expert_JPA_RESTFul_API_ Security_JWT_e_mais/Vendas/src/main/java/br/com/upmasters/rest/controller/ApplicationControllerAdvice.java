package br.com.upmasters.rest.controller;

import br.com.upmasters.exception.ApiErros;
import br.com.upmasters.exception.NotFoundDataException;
import br.com.upmasters.exception.PedidoNaoEncontradoException;
import br.com.upmasters.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErros handleMethodNotValidException(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
        .getAllErrors()
        .stream()
        .map(erro -> erro.getDefaultMessage())
        .collect(Collectors.toList());

    return new ApiErros(errors);
  }

}