package com.example.CRUDProject.dto;

import com.example.CRUDProject.entity.Employee;
import com.example.CRUDProject.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeeDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
    public EmployeeDTO(Employee employee) {
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.email = employee.getEmail();
        this.password = employee.getPassword();
        this.role = employee.getRole();
    }

    public EmployeeDTO() {}

}
