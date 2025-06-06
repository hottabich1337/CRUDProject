package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.entity.Employee;
import com.example.CRUDProject.mapper.EmployeeMapper;
import com.example.CRUDProject.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import com.example.CRUDProject.specification.EmployeeSpecifications;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
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

    public EmployeeDTO getEmployeeById(int id) {
        return convertToDTO(employeeRepository.findById(id).get());
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }


    public void addEmployee(EmployeeDTO employeeDto) {
        if (employeeDto == null || employeeDto.getName() == null || employeeDto.getSurname() == null ||
                employeeDto.getEmail() == null || employeeDto.getPassword() == null || employeeDto.getRole() == null) {
            System.out.println("Invalid data");
        }
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDto); // Преобразуем DTO в Entity
        employeeRepository.save(employee);
    }


    public boolean updateEmployeeByEmail(String email, EmployeeDTO updatedEmployee) {
        EmployeeDTO existingEmployee = convertToDTO( employeeRepository.findByEmail(email));
        if (existingEmployee == null) {
            return false;
        }
        Employee employee = employeeRepository.findByEmail(email);
        employee.setName(updatedEmployee.getName() != null ? updatedEmployee.getName() : existingEmployee.getName());
        employee.setSurname(updatedEmployee.getSurname() != null ? updatedEmployee.getSurname() : existingEmployee.getSurname());
        employee.setPassword(updatedEmployee.getPassword() != null ? updatedEmployee.getPassword() : existingEmployee.getPassword());
        employee.setRole(updatedEmployee.getRole() != null ? updatedEmployee.getRole() : existingEmployee.getRole());
        employeeRepository.save(employee);
        return true;
    }

    //Метод для фильтрации и сортировки
    public List<EmployeeDTO> filterAndSortEmployees(
            String name, String surname, String email, String role,
            String filter, String sort
    ) {
        // Вызываем метод репозитория для фильтрации и сортировки
        List<Employee> employees = employeeRepository.filterAndSortEmployees(name, surname, email, role, filter, sort);

        // Преобразуем результат в DTO
        return employees.stream()
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }


    // Метод для фильтрации, сортировки и пагинации
    /*  public Page<EmployeeDTO> filterAndSortByName(
            String name,
            String sortDirection,
            Pageable pageable
    ) {
        // Создание спецификации для фильтрации по имени
        Specification<Employee> specification = EmployeeSpecifications.nameContains(name);

        // Настройка сортировки
        Sort.Direction direction = Optional.ofNullable(sortDirection)
                .map(dir -> dir.toUpperCase())
                .filter(dir -> dir.equals("ASC") || dir.equals("DESC"))
                .map(Sort.Direction::fromString)
                .orElse(Sort.Direction.ASC); // Значение по умолчанию

        Sort sort = Sort.by(direction, "name");

        // Создание Pageable с учётом сортировки
        Pageable finalPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );

        // Выполнение запроса
        Page<Employee> employeePage = employeeRepository.findAll(specification, finalPageable);

        // Преобразование в DTO
        return employeePage.map(employeeMapper::employeeToEmployeeDTO);
    }
*/

    // Метод для фильтрации, сортировки и пагинации
    public Page<EmployeeDTO> filterAndSortEmployees(
            String name, String surName ,String email ,String role,
            String sortField, String sortDirection,
            Pageable pageable
    ) {
        // Создание спецификации для фильтрации
        Specification<Employee> specification = EmployeeSpecifications.filterBy(name,surName,email, role);

        // Настройка сортировки
        Sort.Direction direction = Optional.ofNullable(sortDirection)
                .map(dir -> dir.toUpperCase())
                .filter(dir -> dir.equals("ASC") || dir.equals("DESC"))
                .map(Sort.Direction::fromString)
                .orElse(Sort.Direction.ASC); // Значение по умолчанию

        Sort sort = Sort.by(direction, sortField);

        // Создание Pageable с учётом сортировки
        Pageable finalPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );

        // Выполнение запроса
        Page<Employee> employeePage = employeeRepository.findAll(specification, finalPageable);

        // Преобразование в DTO
        return employeePage.map(employeeMapper::employeeToEmployeeDTO);
    }


    public void deleteEmployee(String email) {
        if (!employeeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Employee with email " + email + " does not exist");
        }
        System.out.println("Deleting employee with email " + email);
        employeeRepository.removeEmployeeByEmail(email);
    }

}
