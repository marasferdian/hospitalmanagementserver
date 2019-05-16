package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.model.Employee;
import com.cmedresearch.officemaptool.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class EmployeeController {
  private EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  private static void addLinks(Employee employee, Authentication authentication) {
    employee.removeLinks();
    employee.add(
        linkTo(methodOn(EmployeeController.class).getEmployee(employee.getEmployeeId(), authentication)).withSelfRel()
    );
    if (authentication != null) {
      employee.add(
          linkTo(methodOn(EmployeeController.class).editEmployee(employee.getEmployeeId(), employee, authentication)).withRel("update"),
          linkTo(methodOn(EmployeeController.class).deleteEmployee(employee.getEmployeeId())).withRel("delete")
      );
    }
  }

  @GetMapping("/employees")
  public ResponseEntity getEmployees(Authentication authentication) {
    List<Employee> employees = employeeService.getEmployees();
    for (Employee employee : employees) {
      addLinks(employee, authentication);
    }
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping("/unassignedEmployees")
  public ResponseEntity getUnassignedEmployees(Authentication authentication) {
    List<Employee> employees = employeeService.getUnassignedEmployees();
    for (Employee employee : employees) {
      addLinks(employee, authentication);
    }
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @PostMapping("/employees")
  public ResponseEntity createEmployee(@RequestBody Employee employee, Authentication authentication) {
    employee = employeeService.createEmployee(employee);
    addLinks(employee, authentication);
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }

  @GetMapping("/employee/{employeeId}")
  public ResponseEntity getEmployee(@PathVariable Long employeeId, Authentication authentication) {
    Employee employee = employeeService.getEmployee(employeeId);
    addLinks(employee, authentication);
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }

  @PutMapping("/employee/{employeeId}")
  public ResponseEntity editEmployee(@PathVariable Long employeeId, @RequestBody Employee employee, Authentication authentication) {
    employee = employeeService.editEmployee(employeeId, employee);
    addLinks(employee, authentication);
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }

  @DeleteMapping("/employee/{employeeId}")
  public ResponseEntity deleteEmployee(@PathVariable Long employeeId) {
    employeeService.deleteEmployee(employeeId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
