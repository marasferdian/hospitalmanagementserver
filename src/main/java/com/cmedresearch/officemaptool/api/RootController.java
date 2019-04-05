package com.cmedresearch.officemaptool.api;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class RootController {
  @GetMapping("")
  public ResponseEntity getRootObject() {
    ResourceSupport res = new ResourceSupport();
    res.add(
        linkTo(methodOn(OfficeController.class).getOffices()).withRel("offices"),
        linkTo(methodOn(OfficeController.class).createOffice(null)).withRel("createOffice"),
        linkTo(methodOn(EmployeeController.class).getEmployees()).withRel("employees"),
        linkTo(methodOn(EmployeeController.class).getUnassignedEmployees()).withRel("unassignedEmployees"),
        linkTo(methodOn(EmployeeController.class).createEmployee(null)).withRel("createEmployee")
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
}
