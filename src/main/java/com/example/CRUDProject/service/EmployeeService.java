package com.example.CRUDProject.service;

import com.example.CRUDProject.entity.Employee;
import com.example.CRUDProject.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).get();
    }

    public Employee addEmployee(Employee employee) {
        if (employee == null || employee.getName() == null || employee.getSurname() == null ||
                employee.getEmail() == null || employee.getPassword() == null || employee.getRole() == null) {
            System.out.println("Invalid data");
        }
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(String email) {
        if (!employeeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Employee with email " + email + " does not exist");
        }
        System.out.println("Deleting employee with email " + email);
        employeeRepository.removeEmployeeByEmail(email);
    }


    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


}
