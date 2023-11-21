package com.example.backend.models.entity;

import jakarta.persistence.*;

@Entity
public class Purchase {
    @EmbeddedId
    private PurchaseKey id;
    @OneToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @MapsId("houseId")
    @JoinColumn(name = "house_id")
    private House house;

}
