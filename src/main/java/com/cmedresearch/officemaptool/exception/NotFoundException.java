package com.cmedresearch.officemaptool.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException() {
    super("Object not found");
  }
}
