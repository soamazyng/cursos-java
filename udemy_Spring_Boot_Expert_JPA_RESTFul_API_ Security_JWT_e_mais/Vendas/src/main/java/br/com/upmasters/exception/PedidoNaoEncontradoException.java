package br.com.upmasters.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

  public PedidoNaoEncontradoException() {
    super("Pedido não encontrado");
  }
}