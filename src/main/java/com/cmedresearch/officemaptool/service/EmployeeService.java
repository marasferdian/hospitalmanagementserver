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

    private Employee fetchEmployee(Long employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee == null) {
            throw new RuntimeException();
        }
        return employee;
    }

    public Employee getEmployee(Long employeeId) {
        return fetchEmployee(employeeId);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        fetchEmployee(employeeId);
        employeeRepository.deleteByEmployeeId(employeeId);
    }

    public Employee editEmployee(Long employeeId, Employee newEmp) {
        Employee employee = fetchEmployee(employeeId);
        employee.setFirstName(newEmp.getFirstName());
        employee.setLastName(newEmp.getLastName());
        employee.setDepartment(newEmp.getDepartment());
        employee.setJobTitle(newEmp.getJobTitle());
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
        return IteratorUtils.toList(employeeRepository.findAll().iterator());
    }
}
