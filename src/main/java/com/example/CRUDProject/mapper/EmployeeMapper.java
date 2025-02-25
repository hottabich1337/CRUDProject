package com.example.CRUDProject.mapper;


import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO employeeToEmployeeDTO(Employee employee);
    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);
}
