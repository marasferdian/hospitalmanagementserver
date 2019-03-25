package com.cmedresearch.officemaptool.persistence;

import com.cmedresearch.officemaptool.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByEmployeeId(Long employeeId);
    void deleteByEmployeeId(Long employeeId);
}
