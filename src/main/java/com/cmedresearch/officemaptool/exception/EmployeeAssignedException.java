package com.cmedresearch.officemaptool.exception;

public class EmployeeAssignedException extends RuntimeException {
  public EmployeeAssignedException() {
    super("The employee has already been assigned");
  }
}
