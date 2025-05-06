package com.example.CRUDProject.entity;


import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.util.Date;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date orderCreationDate;

    @Column
    private String orderStatus;

    @ManyToOne
    private Client client;


}
