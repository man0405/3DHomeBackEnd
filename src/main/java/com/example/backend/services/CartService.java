package com.example.backend.services;

import com.example.backend.models.entity.Cart;

public interface CartService {
    void save(int customerId, int furnitureId, int quantity);


    Cart findById(Long Id);

    void delete(int customerId, int furnitureId);
}
