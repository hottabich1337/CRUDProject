package com.example.CRUDProject.controller;

import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CRUDProject.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;
//выбрасывать исключения
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
//входные и выходные данные это DTO
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody EmployeeDTO employeeDTO) {

        return employeeService.addEmployee(employeeDTO);
    }
//здесь нужен PutMapping
    @PutMapping("/updateEmployee")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDTO updatedEmployee){
        String email = updatedEmployee.getEmail();
        boolean isUpdated = employeeService.updateEmployeeByEmail(email, updatedEmployee);
        if (isUpdated) {
            return ResponseEntity.ok("Employee with email " + email + " has been updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//здесь нужен DeleteMapping
    @DeleteMapping("/deleteEmployee")
    public void deleteEmployee(@RequestParam String email) {
        employeeService.deleteEmployee(email);
    }


    @GetMapping("/employeeInfo")
    public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam String email) {
        Employee employee = employeeService.getEmployeeByEmail(email);
        return ResponseEntity.ok(employee);
    }

//Фильтрация должна быть в одном контроллере
    @GetMapping("/filterByName")
    public ResponseEntity<List<Employee>> filterEmployeeByName(@RequestParam String name) {
        List<Employee> sortedEmployeeList = employeeService.filterEmployeeByName(name);
        return ResponseEntity.ok(sortedEmployeeList);
    }

    @GetMapping("/filter")
    public List<EmployeeDTO> filterAndSortEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role
    ) {
        return employeeService.filterAndSortEmployees(name, surname, email, role);
    }

    @GetMapping("/filterBySurName")
    public ResponseEntity<List<Employee>> filterEmployeeBySurName(@RequestParam String surName) {
        List<Employee> sortedEmployeeList = employeeService.filterEmployeeBySurName(surName);
        return ResponseEntity.ok(sortedEmployeeList);
    }


    @GetMapping("/filterByEmail")
    public ResponseEntity<List<Employee>> filterEmployeeByEmail(@RequestParam String email) {
        List<Employee> sortedEmployeeList = employeeService.getAllEmployees().stream().filter(employee -> employee.getEmail().contains(email)).collect(Collectors.toList());
        return ResponseEntity.ok(sortedEmployeeList);
    }


    @GetMapping("/filterByRole")
    public ResponseEntity<List<Employee>> filterEmployeeByRole (@RequestParam String role) {
        List<Employee> sortedEmployeeList = employeeService.getAllEmployees().stream().filter(employee -> employee.getRole().contains(role)).collect(Collectors.toList());
        return ResponseEntity.ok(sortedEmployeeList);
    }

  /*  @GetMapping("/sortByName")
    public ResponseEntity<List<Employee>> sortEmployeeByName() {
  //      List<Employee> employees = employeeService.getAllEmployees().stream().sorted()filter(employee -> employee.getName().).collect(Collectors.toList());
     //   return ResponseEntity.ok(employees);
    }*/

    @GetMapping("/home")
    public String home() {
        return "Welcome to the home page!";
    }
}
