package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity getEmployees(@PathVariable Integer employeeId) {
        return new ResponseEntity<>(employeeService.getAllEmployeesInOffice(employeeId), HttpStatus.OK);
    }
}
