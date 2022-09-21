package br.com.upmasters.exception;

public class SenhaInvalidaException extends RuntimeException {
  public SenhaInvalidaException() {
    super("Senha inválida");
  }
}