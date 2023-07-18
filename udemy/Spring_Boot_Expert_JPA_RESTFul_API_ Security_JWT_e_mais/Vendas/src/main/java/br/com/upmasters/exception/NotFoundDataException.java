package br.com.upmasters.exception;

public class NotFoundDataException extends RuntimeException {
  public NotFoundDataException(String message) {
    super(message);
  }
}
