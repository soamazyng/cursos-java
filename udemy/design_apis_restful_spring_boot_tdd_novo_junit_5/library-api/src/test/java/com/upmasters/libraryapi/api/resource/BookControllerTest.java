package com.upmasters.libraryapi.api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upmasters.libraryapi.api.dto.BookDTO;
import com.upmasters.libraryapi.entity.Book;
import com.upmasters.libraryapi.exception.BusinessException;
import com.upmasters.libraryapi.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

  static String BOOK_API = "/api/books";

  @Autowired
  MockMvc mvc;

  @MockBean
  BookService service;

  @Test
  @DisplayName("Deve criar um livro com sucesso")
  public void createBookTest() throws Exception{

    BookDTO bookDTO = createNewBook();

    Book savedBook = Book.builder()
        .id(1L)
        .author("Artur")
        .title("As aventuras")
        .isbn("001")
        .build();

    BDDMockito.given(service.save(Mockito.any(Book.class)))
        .willReturn(savedBook);

    String json = new ObjectMapper().writeValueAsString(bookDTO);

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
        .post(BOOK_API)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(json);

    mvc.perform(request)
        .andExpect(status().isCreated())
        .andExpect(jsonPath("id").isNotEmpty())
        .andExpect(jsonPath("title").value(bookDTO.getTitle()))
        .andExpect(jsonPath("author").value(bookDTO.getAuthor()))
        .andExpect(jsonPath("isbn").value(bookDTO.getIsbn()));

  }

  @Test
  @DisplayName("Deve retornar um erro ao criar um livro sem enviar os dados")
  public void createInvalidBookTest() throws Exception{

    String json = new ObjectMapper().writeValueAsString(new BookDTO());

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
        .post(BOOK_API)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(json);

    mvc.perform(request)
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("errors", hasSize(3)));

  }

  @Test
  @DisplayName("Deve lançar erro ao tentar cadastrar um livro com isbn duplicado")
  public void createBookWithDuplicatedIsbn() throws Exception {

    BookDTO bookDTO = createNewBook();
    String msgErro = "Isbn já cadastrado.";

    BDDMockito.given(service.save(Mockito.any(Book.class)))
        .willThrow(new BusinessException(msgErro));

    String json = new ObjectMapper().writeValueAsString(bookDTO);

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
        .post(BOOK_API)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(json);

    mvc.perform(request)
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("errors", hasSize(1)))
        .andExpect(jsonPath("errors[0]").value(msgErro));

  }

  @Test
  @DisplayName("Deve obter informações de um livro.")
  public void getBookDetailsTest() throws Exception {

    // given
    Long id = 1l;

    final Book book = Book.builder()
        .id(id)
        .isbn("123")
        .author("Fulano")
        .title("As Aventuras")
        .build();

    BDDMockito.given(service.getById(id)).willReturn(Optional.of(book));

    // when
    final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
        .get(BOOK_API.concat("/" + id))
        .accept(MediaType.APPLICATION_JSON);

    mvc
        .perform(request)
        .andExpect(status().isOk())
        .andExpect(jsonPath("id").value(id))
        .andExpect(jsonPath("isbn").value("123"))
        .andExpect(jsonPath("author").value("Fulano"))
        .andExpect(jsonPath("title").value("As Aventuras"))
        ;

  }

  private BookDTO createNewBook() {
    BookDTO bookDTO = BookDTO.builder()
        .author("Artur")
        .title("As aventuras")
        .isbn("001")
        .build();
    return bookDTO;
  }

}