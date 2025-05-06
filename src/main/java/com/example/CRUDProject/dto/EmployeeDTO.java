package com.example.CRUDProject.dto;

import com.example.CRUDProject.entity.Employee;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeeDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;
    public EmployeeDTO(Employee employee) {
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.email = employee.getEmail();
        this.password = employee.getPassword();
        this.role = employee.getRole();
    }

    public EmployeeDTO() {}
    /*public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
    */

}
