package com.example.CRUDProject.dto;


import com.example.CRUDProject.entity.Client;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.Date;

@Data
public class OrderDTO {

    private int id;
    private LocalDate orderCreationDate;
    private String orderStatus;
    private Integer clientId;

}