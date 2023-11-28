package com.example.backend.models.entity;

import jakarta.persistence.Column;

public class VisitKey {
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "house_id")
    private Long houseId;

}
