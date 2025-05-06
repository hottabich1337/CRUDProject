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
    @PostMapping()
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok(employeeDTO);
    }
//здесь нужен PutMapping
    @PutMapping()
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


    @GetMapping()
    public ResponseEntity<EmployeeDTO> getEmployeeById(@RequestParam Integer id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDTO);
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

}
