package com.Assignment.CaseStudy1.Controller;

import com.Assignment.CaseStudy1.Entity.Employee;
import com.Assignment.CaseStudy1.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> CreateEmployee(@RequestBody Employee employee){
        try {
            Employee newEmployee = employeeService.saveEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
        }catch (Exception e){
            e.getMessage();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
        return optionalEmployee.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee){
        Optional<Employee> optionalEmployee = employeeService.updateEmployeeById(id,employee);
        return optionalEmployee.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
