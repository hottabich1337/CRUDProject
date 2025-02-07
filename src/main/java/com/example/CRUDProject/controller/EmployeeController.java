package com.example.CRUDProject.controller;

import com.example.CRUDProject.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.CRUDProject.service.EmployeeService;

@RestController
@RequestMapping("/home")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {

        return employeeService.addEmployee(employee);
    }

    @PostMapping("/updateEmployee")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.getEmployeeById(employee.getId());
    }


    @PostMapping("/deleteEmployee")
    public void deleteEmployee(@RequestParam String email) {
        employeeService.deleteEmployee(email);
    }




    @GetMapping("/home")
    public String home() {
        return "Welcome to the home page!";
    }
}
