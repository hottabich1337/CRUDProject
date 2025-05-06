package com.example.CRUDProject.dto;

import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Employee;
import com.example.CRUDProject.entity.Order;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ClientDTO {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private List<Order> orders;


    public ClientDTO(Client client) {
        this.name = client.getName();
        this.surname = client.getSurname();
        this.email = client.getEmail();
    }

    public ClientDTO() {}



    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
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


}
