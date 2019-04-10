package com.cmedresearch.officemaptool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity notFoundException(RuntimeException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EmployeeAssignedException.class)
  public ResponseEntity unprocessableEntityException(RuntimeException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
