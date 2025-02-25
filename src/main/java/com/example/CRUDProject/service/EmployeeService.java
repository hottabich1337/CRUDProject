package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.entity.Employee;
import com.example.CRUDProject.mapper.EmployeeMapper;
import com.example.CRUDProject.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee);
    }

    public Employee convertToEntity(EmployeeDTO dto) {
        Employee employee ;
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            employee = employeeRepository.findByEmail(dto.getEmail());
        }else {
            employee = new Employee();
        }
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setEmail(dto.getEmail());
        employee.setRole(dto.getRole());
        employee.setPassword(dto.getPassword());
        return employee;
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).get();
    }
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }


    public Employee addEmployee(EmployeeDTO employeeDto) {
        if (employeeDto == null || employeeDto.getName() == null || employeeDto.getSurname() == null ||
                employeeDto.getEmail() == null || employeeDto.getPassword() == null || employeeDto.getRole() == null) {
            System.out.println("Invalid data");
        }
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDto); // Преобразуем DTO в Entity
        return employeeRepository.save(employee);
    }


    public List<Employee> filterEmployeeByName(String name) {
        return employeeRepository.findAll().stream().filter(employee -> employee.getName().contains(name)).collect(Collectors.toList());
    }

    public List<Employee> filterEmployeeBySurName(String surName) {
        return employeeRepository.findAll().stream().filter(employee -> employee.getSurname().contains(surName)).collect(Collectors.toList());
    }

    public List<Employee> filterEmployeeByEmail(String Email) {
        return employeeRepository.findAll().stream().filter(employee -> employee.getEmail().contains(Email)).collect(Collectors.toList());
    }

    public List<Employee> filterEmployeeByRole(String role) {
        return employeeRepository.findAll().stream().filter(employee -> employee.getRole().contains(role)).collect(Collectors.toList());
    }


    public boolean updateEmployeeByEmail(String email, EmployeeDTO updatedEmployee) {
        EmployeeDTO existingEmployee = convertToDTO( employeeRepository.findByEmail(email));
        if (existingEmployee == null) {
            return false;
        }

        existingEmployee.setName(updatedEmployee.getName() != null ? updatedEmployee.getName() : existingEmployee.getName());
        existingEmployee.setSurname(updatedEmployee.getSurname() != null ? updatedEmployee.getSurname() : existingEmployee.getSurname());
        existingEmployee.setPassword(updatedEmployee.getPassword() != null ? updatedEmployee.getPassword() : existingEmployee.getPassword());
        existingEmployee.setRole(updatedEmployee.getRole() != null ? updatedEmployee.getRole() : existingEmployee.getRole());
        Employee employee = employeeMapper.employeeDTOToEmployee(existingEmployee); // Преобразуем DTO в Entity
        employeeRepository.save(employee);
        return true;
    }

    public List<EmployeeDTO> filterAndSortEmployees(String name, String surname, String email, String role) {
        // Получаем всех сотрудников
        List<Employee> employees = employeeRepository.findAll();

        // Фильтрация и сортировка с помощью Stream API
        return employees.stream()
                .filter(e -> name == null || e.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(e -> surname == null || e.getSurname().toLowerCase().contains(surname.toLowerCase()))
                .filter(e -> email == null || e.getEmail().toLowerCase().contains(email.toLowerCase()))
                .filter(e -> role == null || e.getRole().toLowerCase().equals(role.toLowerCase()))
                .sorted((e1, e2) -> {
                    int nameCompare = e1.getName().compareTo(e2.getName());
                    if (nameCompare != 0) return nameCompare;
                    return e1.getRole().compareTo(e2.getRole());
                })
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public void deleteEmployee(String email) {
        if (!employeeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Employee with email " + email + " does not exist");
        }
        System.out.println("Deleting employee with email " + email);
        employeeRepository.removeEmployeeByEmail(email);
    }

}
