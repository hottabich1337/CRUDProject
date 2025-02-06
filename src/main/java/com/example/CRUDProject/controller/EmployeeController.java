package com.example.CRUDProject.controller;

import com.example.CRUDProject.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CRUDProject.service.EmployeeService;

@RestController
@RequestMapping("/home")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        if (employee == null || employee.getName() == null || employee.getSurname() == null ||
                employee.getEmail() == null || employee.getPassword() == null || employee.getRole() == null) {
            System.out.println("Invalid data");
        }
        return employeeService.addEmployee(employee);
    }





    @GetMapping("/home")
    public String home() {
        return "Welcome to the home page!";
    }
}
