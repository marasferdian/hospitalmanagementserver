package com.cmedresearch.officemaptool.api;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class RootController {
  @GetMapping("")
  public ResponseEntity getRootObject(Authentication authentication) {
    ResourceSupport res = new ResourceSupport();
    res.add(
        linkTo(methodOn(OfficeController.class).getOffices(authentication)).withRel("offices"),
        linkTo(methodOn(EmployeeController.class).getEmployees(authentication)).withRel("employees"),
        linkTo(methodOn(EmployeeController.class).getUnassignedEmployees(authentication)).withRel("unassignedEmployees")
    );
    if (authentication != null) {
      res.add(
          linkTo(methodOn(OfficeController.class).createOffice(null, authentication)).withRel("createOffice"),
          linkTo(methodOn(EmployeeController.class).createEmployee(null, authentication)).withRel("createEmployee")
      );
    }
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
}
