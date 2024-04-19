package com.example.backend.models.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Furniture {
    @Id
    private Long id;

    private String name;

    private String color;

    private String material;

    private int warranty;
}
