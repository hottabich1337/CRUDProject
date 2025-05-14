package com.example.CRUDProject.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Primary;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date orderCreationDate;

    @Column
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Client client;


}
