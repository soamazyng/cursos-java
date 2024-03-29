package com.upmasters.libraryapi.service;

import com.upmasters.libraryapi.entity.Book;

import java.util.Optional;

public interface BookService {
  Book save(Book book);

  Optional<Book> getById(Long id);
}