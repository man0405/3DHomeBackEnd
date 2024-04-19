package com.example.backend.models.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.sql.Time;

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

    @Temporal(TemporalType.TIMESTAMP)
    public java.util.Date date;

    @CreationTimestamp
    @Column (updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private java.sql.Timestamp lastModifiedDate;

}
