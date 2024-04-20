package com.example.backend.services;

import com.example.backend.models.entity.Cart;

import java.util.List;

public interface CartService {
    void save(int customerId, int furnitureId, int quantity);


    List<Cart> findByCustomerId(Long Id);

    void delete(int customerId, int furnitureId);
}
