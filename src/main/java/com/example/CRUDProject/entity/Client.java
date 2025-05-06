package com.example.CRUDProject.entity;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.dto.EmployeeDTO;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "client")
public class Client {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "surname", nullable = false, length = 255)
    private String surname;

    @Column(name = "email", nullable = false, length = 255, unique = true) // NOT NULL
    private String email;

    @Column(name = "phoneNumber", nullable = false, length = 255, unique = true) // NOT NULL
    private String phoneNumber;

    @OneToMany
    @Column(name = "orderList", nullable = false, length = 255, unique = true) // NOT NULL
    private List<Order> orders;


    public ClientDTO convertToDTO(Client client) {
        return new ClientDTO(client);
    }
}
