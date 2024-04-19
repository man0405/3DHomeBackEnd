package com.example.backend.models.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cart {

    @Id
    private Long id;

    @ManyToOne
//    @JsonManagedReference
    @JoinColumn(name = "furniture_Id")
    private Furniture furniture;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
