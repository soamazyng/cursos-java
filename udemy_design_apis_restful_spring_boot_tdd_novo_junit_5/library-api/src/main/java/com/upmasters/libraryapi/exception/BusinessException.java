package com.upmasters.libraryapi.exception;

public class BusinessException extends RuntimeException {
  public BusinessException(final String msg) {
    super(msg);
  }
}