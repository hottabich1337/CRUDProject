package com.example.CRUDProject.controller;

import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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


   /* @GetMapping("/filter")
    public ResponseEntity<Page<EmployeeDTO>> filterAndSortEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role,
            @RequestParam(required = false, defaultValue = "name") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @PageableDefault(page = 0, size = 2) Pageable pageable
    ) {
        try {
            Page<EmployeeDTO> result = employeeService.filterAndSortEmployees(name, surname, email, role, sortField, sortDirection, pageable);
            if (result.isEmpty()) {
                return ResponseEntity.noContent().build(); // Возвращаем 204 No Content, если результат пустой
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Возвращаем 500 Internal Server Error
        }
    }*/

    @GetMapping("/filter-by-name")
    public Page<EmployeeDTO> filterAndSortByName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @PageableDefault(page = 0, size = 3) Pageable pageable
    ) {
        return employeeService.filterAndSortByName(name, sortDirection, pageable);
    }



    @GetMapping("/filter")
    public Page<EmployeeDTO> filterAndSortEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role,
            @RequestParam(required = false, defaultValue = "name") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @PageableDefault(page = 2, size = 3) Pageable pageable
    ) {
        return employeeService.filterAndSortEmployees(name,surName,email, role, sortField, sortDirection, pageable);
    }
   /* @GetMapping("/sortByName")
    public ResponseEntity<List<Employee>> sortEmployeeByName() {
        List<Employee> employees = employeeService.getAllEmployees().stream().sorted().filter(employee -> employee.getName().).collect(Collectors.toList());
        return ResponseEntity.ok(employees);
    }*/

    @GetMapping("/home")
    public String home() {
        return "Welcome to the home page!";
    }
}
