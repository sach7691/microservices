package com.ab.controllers;

import com.ab.models.Employee;
import com.ab.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController { //REST API

    @Autowired
    private EmployeeService employeeService;

    //Insert Employee Details
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addOrUpdateEmployee(@RequestBody Employee employee) {
        return employeeService.insertEmployee(employee);
    }

    //Display All Employee Details
    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> findAll() {
        return employeeService.findAllEmployee();
    }

    //Find Employee by Id
    @GetMapping("/find/{id}")
    public Optional<Employee> findEmployeeById(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    //Delete an Employee by Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnEmployee(@PathVariable Integer id) {
        Optional<Employee> emp = employeeService.findById(id);
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Deleted", HttpStatus.OK);
    }


}
