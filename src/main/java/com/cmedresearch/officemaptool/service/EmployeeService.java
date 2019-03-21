package com.cmedresearch.officemaptool.service;

import com.cmedresearch.officemaptool.model.Employee;
import com.cmedresearch.officemaptool.persistence.EmployeeRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee == null) {
            throw new RuntimeException();
        }
        return employee;
    }

    public List<Employee> getAllEmployeesInOffice(Integer employeeId) {
        return IteratorUtils.toList(employeeRepository.findAllByEmployeeId(employeeId).iterator());
    }
}
