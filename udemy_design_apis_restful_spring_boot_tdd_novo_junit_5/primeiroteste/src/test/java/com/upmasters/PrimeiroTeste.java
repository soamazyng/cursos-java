package com.upmasters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PrimeiroTeste {

  @Mock
  Calculadora calculadora;

  int numero1 = 10, numero2 = 5;

  @BeforeEach
  public void setUp(){
    calculadora = new Calculadora();
  }

  @Test
  public void deveSomar2Numeros(){

    // execução
    int resultado = calculadora.somar(numero1, numero2);

    // verificação
    Assertions.assertThat(resultado).isEqualTo(15);

  }

  @Test
  public void deveSubtrair2Numeros(){

    // execução
    int resultado = calculadora.subtracao(numero1, numero2);

    // verificação
    Assertions.assertThat(resultado).isEqualTo(5);

  }

  @Test
  public void deveMultiplicar2Numeros(){

    // execução
    int resultado = calculadora.multiplicacao(numero1, numero2);

    // verificação
    Assertions.assertThat(resultado).isEqualTo(50);

  }

  @Test
  public void naoDeveSomarNumerosNegativos(){

    numero1 = -10;

    org.junit.jupiter.api.Assertions
        .assertThrows(RuntimeException.class,
            () -> calculadora.somar(numero1, numero2));
  }

  @Test
  public void deveDividir2Numeros(){

    int resultado = calculadora.divisao(numero1, numero2);

    Assertions.assertThat(resultado).isEqualTo(2);
  }

  @Test
  public void naoDeveDividirPorZero(){

    numero2 = 0;

    org.junit.jupiter.api.Assertions
        .assertThrows(RuntimeException.class, () -> calculadora.divisao(numero1, numero2));
  }

}

class Calculadora {
  int somar(int num, int num2){

    if(num < 0 || num2 < 0)
      throw new RuntimeException("Erro ao calcular números negativos");

    return num + num2;
  }

  int subtracao(int num, int num2){

    return num - num2;
  }

  int multiplicacao(int num, int num2){

    return num * num2;
  }

  int divisao(int num, int num2){

    if(num2 <= 0 )
      throw new RuntimeException("Erro você não pode fazer uma divisão por zero ou números negativos");

    return num / num2;
  }

}