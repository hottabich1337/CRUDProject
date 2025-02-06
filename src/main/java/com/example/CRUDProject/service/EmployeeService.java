package com.example.CRUDProject.service;

import com.example.CRUDProject.Entity.Employee;
import com.example.CRUDProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

 /*   public Employee getEmployeeByEMail(String mail) {
        return employeeRepository.findByEmail(mail);
    }

  */

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).get();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }


    /*  public Employee createEmployee(Employee employee) {
        System.out.printf("Creating employee %s\n", employee);
        Employee newEmployee = new Employee();
        return newEmployee;

    }*/


}
