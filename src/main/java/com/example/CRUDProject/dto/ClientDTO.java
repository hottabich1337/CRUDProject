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
    private String phone;
    private List<Order> orders;


    public ClientDTO(Client client) {
        this.name = client.getName();
        this.surname = client.getSurname();
        this.email = client.getEmail();
        this.phone = client.getPhone();
        this.orders = client.getOrders();
    }

    public ClientDTO() {}

}
