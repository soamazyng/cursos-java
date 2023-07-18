package com.upmasters.libraryapi.api.exception;

import com.upmasters.libraryapi.exception.BusinessException;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class ApiErrors {

  private List<String> errors;
  public ApiErrors(final BindingResult bindingResult) {

    this.errors = new ArrayList<>();

    bindingResult.getAllErrors().forEach( e -> this.errors.add(e.getDefaultMessage()) );

  }

  public ApiErrors(final BusinessException ex) {
    this.errors = new ArrayList<>();
    this.errors.add(ex.getMessage());
  }

  public List<String> getErrors(){
    return errors;
  }
}