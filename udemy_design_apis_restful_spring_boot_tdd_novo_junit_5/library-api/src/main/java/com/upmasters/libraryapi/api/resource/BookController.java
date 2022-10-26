package com.upmasters.libraryapi.api.resource;

import com.upmasters.libraryapi.api.dto.BookDTO;
import com.upmasters.libraryapi.api.exception.ApiErrors;
import com.upmasters.libraryapi.entity.Book;
import com.upmasters.libraryapi.exception.BusinessException;
import com.upmasters.libraryapi.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {

  private final BookService service;

  private final ModelMapper modelMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookDTO create(@RequestBody @Valid BookDTO bookDTO){

    var book = modelMapper.map(bookDTO, Book.class);
    final Book entity = service.save(book);
    return modelMapper.map(entity, BookDTO.class);

  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookDTO get(@RequestParam Long id){

    final Optional<Book> entity = service.getById(id);

    if(entity.isEmpty())
      throw new BusinessException("Livro com este ID não encontrado");

    return modelMapper.map(entity, BookDTO.class);

  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleValidationExceptions(MethodArgumentNotValidException ex){
    final BindingResult bindingResult = ex.getBindingResult();
    return new ApiErrors(bindingResult);
  }

  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleBusinessException(BusinessException ex){
    return new ApiErrors(ex);
  }

}