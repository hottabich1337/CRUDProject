package com.example.CRUDProject.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private double price;
}
