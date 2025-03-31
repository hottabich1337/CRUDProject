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

//    public List<EmployeeDTO> filterAndSortEmployees(
//            String name, String surname, String email, String role,
//            String filter, String sort
//    ) {
//        // Получаем всех сотрудников
//        List<Employee> employees = employeeRepository.findAll();
//
//        // Фильтрация
//        employees = employees.stream()
//                .filter(e -> {
//                    if ("name".equals(filter) && name != null && !e.getName().toLowerCase().contains(name.toLowerCase())) {
//                        return false;
//                    }
//                    if ("surname".equals(filter) && surname != null && !e.getSurname().toLowerCase().contains(surname.toLowerCase())) {
//                        return false;
//                    }
//                    if ("email".equals(filter) && email != null && !e.getEmail().toLowerCase().contains(email.toLowerCase())) {
//                        return false;
//                    }
//                    if ("role".equals(filter) && role != null && !e.getRole().toLowerCase().equals(role.toLowerCase())) {
//                        return false;
//                    }
//                    return true;
//                })
//                .collect(Collectors.toList());
//
//        // Сортировка
//        if ("name".equals(sort)) {
//            employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
//        } else if ("role".equals(sort)) {
//            employees.sort((e1, e2) -> e1.getRole().compareTo(e2.getRole()));
//        }
//
//        // Преобразуем в DTO
//        return employees.stream()
//                .map(employeeMapper::employeeToEmployeeDTO)
//                .collect(Collectors.toList());
//    }



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
  /*  public List<EmployeeDTO> filterAndSortEmployees(
            String name, String surname, String email, String role,
            String filterField, // Поле для фильтрации
            String sortField,   // Поле для сортировки
            String sortDirection, // Направление (ASC/DESC)
            Pageable pageable
    ) {

        // Формируем сортировку
        Sort sort = createSort(sortField, sortDirection);
        Pageable finalPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );

        // Выполняем запрос с пагинацией
        Page<Employee> employeePage = employeeRepository.findAll(finalPageable);

        // Преобразуем в DTO
        return employeePage.getContent().stream()
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }
*/



    public Page<EmployeeDTO> filterAndSortEmployees(
            String name, String surname, String email, String role,
            String sortField, String sortDirection,
            Pageable pageable
    ) {
        // Формируем Sort
        Sort.Direction direction = Sort.Direction.fromString(sortDirection.toUpperCase());
        Sort sort = Sort.by(direction, sortField);

        // Создаем Pageable с учетом сортировки
        Pageable finalPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );

        // Выполняем запрос с фильтрацией
        Page<Employee> employeePage = employeeRepository.findAllByNameContainingAndSurnameContainingAndEmailContainingAndRoleEquals(
                name, surname, email, role,
                finalPageable
        );

        // Преобразуем в DTO
        return employeePage.map(employeeMapper::employeeToEmployeeDTO);
    }



    // Вспомогательный метод для создания Sort
    private Sort createSort(String sortField, String sortDirection) {
        String defaultSortField = "name";
        String defaultSortDirection = "ASC";

        String finalSortField = sortField != null && !sortField.isEmpty() ? sortField : defaultSortField;
        String finalSortDirection = sortDirection != null && !sortDirection.isEmpty()
                ? sortDirection.toUpperCase()
                : defaultSortDirection;

        // Проверка допустимых значений направления
        if (!finalSortDirection.equals("ASC") && !finalSortDirection.equals("DESC")) {
            finalSortDirection = defaultSortDirection;
        }

        return Sort.by(Sort.Direction.fromString(finalSortDirection), finalSortField);
    }

    public void deleteEmployee(String email) {
        if (!employeeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Employee with email " + email + " does not exist");
        }
        System.out.println("Deleting employee with email " + email);
        employeeRepository.removeEmployeeByEmail(email);
    }

}
