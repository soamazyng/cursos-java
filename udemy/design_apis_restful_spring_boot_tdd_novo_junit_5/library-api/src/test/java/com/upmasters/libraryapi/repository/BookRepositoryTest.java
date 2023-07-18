package com.upmasters.libraryapi.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import com.upmasters.libraryapi.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class BookRepositoryTest {

  @Autowired
  TestEntityManager testEntityManager;

  @Autowired
  BookRepository bookRepository;

  @Test
  @DisplayName("Deve retornar verdadeiro quando existir um livro na base com o isbn informado")
  public void returnTrueWhenIsbnExists(){

//    cenario
    String isbn = "123";
    final Book book = Book.builder().title("Aventura").author("Fulano").isbn(isbn).build();
    testEntityManager.persist(book);

//    execução
    boolean exists = bookRepository.existsByIsbn(isbn);

//    verificação
    assertThat(exists).isTrue();

  }

  @Test
  @DisplayName("Deve retornar falso quando não existir um livro na base com o isbn informado")
  public void returnFalseWhenIsbnExists(){

//    cenario
    String isbn = "123";

//    execução
    boolean exists = bookRepository.existsByIsbn(isbn);

//    verificação
    assertThat(exists).isFalse();

  }

}