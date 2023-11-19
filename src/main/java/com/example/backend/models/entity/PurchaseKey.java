package com.example.backend.models.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class PurchaseKey implements Serializable{
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "house_id")
    private Long houseId;
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getHouseId() {
        return houseId;
    }
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
    @Override
    public String toString() {
        return "VisitKey{" +
                "customerId=" + customerId +
                ", houseId=" + houseId +
                '}';
    }
}
