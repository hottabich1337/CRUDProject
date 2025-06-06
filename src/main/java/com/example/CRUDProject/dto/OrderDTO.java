package com.example.CRUDProject.dto;


import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.Date;

@Data
public class OrderDTO {

    private int id;
    private LocalDate orderCreationDate;
    private OrderStatus orderStatus;
    private Integer clientId;

}