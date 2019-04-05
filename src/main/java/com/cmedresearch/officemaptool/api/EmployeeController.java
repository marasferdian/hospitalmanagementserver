package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.model.Employee;
import com.cmedresearch.officemaptool.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity getEmployees() {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/unassignedEmployees")
    public ResponseEntity getUnassignedEmployees() {
      return new ResponseEntity<>(employeeService.getUnassignedEmployees(), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity getEmployee(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);
    }

    @PutMapping("/employee/{employeeId}")
    public ResponseEntity editEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.editEmployee(employeeId, employee), HttpStatus.OK);
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
