package com.ab.controllers;

import com.ab.models.Employee;
import com.ab.services.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class EmployeeController { //Consumer (Client)

    @Autowired
    private RestClientService restClientService;

    @PostMapping("/add")
    public String addOrUpdateEmployee(@RequestBody Employee employee){
        return restClientService.insertEmployee(employee);
    }

    @GetMapping("/find")
    public List<Employee> getAll(){
        return restClientService.getEmployee();
    }

    @GetMapping("/find/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Integer id){
        return restClientService.getEmployeeById(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnEmployee(@PathVariable Integer id){
        Optional<Employee> emp = restClientService.getEmployeeById(id);
        restClientService.deleteEmp(id);
        return new ResponseEntity<String>( "Deleted", HttpStatus.OK);
    }


}
