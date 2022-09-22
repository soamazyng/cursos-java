package com.upmasters.libraryapi.service.impl;

import com.upmasters.libraryapi.entity.Book;
import com.upmasters.libraryapi.exception.BusinessException;
import com.upmasters.libraryapi.repository.BookRepository;
import com.upmasters.libraryapi.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
  private final BookRepository repository;

  @Override
  public Book save(final Book book) {

    if(repository.existsByIsbn(book.getIsbn()))
      throw new BusinessException("Isbn já cadastrado.");


    return repository.save(book);
    
  }
}