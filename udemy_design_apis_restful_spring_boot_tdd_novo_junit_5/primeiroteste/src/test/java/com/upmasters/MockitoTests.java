package com.upmasters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoTests {

  @Mock
  List<String> lista;

  @Test
  public void primeiroTesteMockito(){

    when(lista.size()).thenReturn(20);

    int size = lista.size();

    Assertions.assertEquals(size, 20);

  }

}