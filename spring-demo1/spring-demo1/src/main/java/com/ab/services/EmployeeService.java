package com.ab.services;

import com.ab.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Employee insertEmployee(Employee employee);
    public List<Employee> findAllEmployee();
    public Optional<Employee> findById(Integer id);
    public void deleteEmployee(int id);

}
