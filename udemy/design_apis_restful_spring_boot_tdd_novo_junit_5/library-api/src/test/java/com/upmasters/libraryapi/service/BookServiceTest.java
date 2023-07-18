package com.upmasters.libraryapi.service;

import com.upmasters.libraryapi.entity.Book;
import com.upmasters.libraryapi.exception.BusinessException;
import com.upmasters.libraryapi.repository.BookRepository;
import com.upmasters.libraryapi.service.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class BookServiceTest {

  BookService bookService;
  @MockBean
  BookRepository bookRepository;

  @BeforeEach
  public void setUp(){
    this.bookService = new BookServiceImpl(bookRepository);
  }

  @Test
  @DisplayName("Deve salvar um livro")
  void saveBookTest() {

    Book book = getValidBook();

    Book bookSaved = getValidBook();
    bookSaved.setId(1L);

    Mockito.when(bookRepository.save(book)).thenReturn(bookSaved);

    final Book savedBook = bookService.save(book);

    assertThat(savedBook.getId()).isNotNull();
    assertThat(savedBook.getIsbn()).isEqualTo("123");
    assertThat(savedBook.getAuthor()).isEqualTo("Fulano");
    assertThat(savedBook.getTitle()).isEqualTo("As aventuras");

  }

  @Test
  @DisplayName("Deve lançar erro de negócio ao tentar salvar um livro com isbn duplicado")
  public void shouldNotSaveABookWithDuplicatedISBN(){
    Book book = getValidBook();

    Mockito.when(bookRepository.existsByIsbn(Mockito.anyString())).thenReturn(true);

    Throwable exception = Assertions.catchThrowable(() -> bookService.save(book));

    assertThat(exception)
        .isInstanceOf(BusinessException.class)
        .hasMessage("Isbn já cadastrado.");

    Mockito.verify(bookRepository, Mockito.never()).save(book);

  }

  private Book getValidBook() {
    return Book.builder()
        .title("As aventuras")
        .author("Fulano")
        .isbn("123")
        .build();
  }
}