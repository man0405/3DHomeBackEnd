package com.example.backend.services;

import com.example.backend.dto.CartResponse;
import com.example.backend.models.entity.Cart;
import com.example.backend.models.entity.Item;

import java.util.List;

public interface CartService {

    void addItem(int customerId, int furnitureId, int quantity);

    CartResponse getCart(int customerId);
//    void save(int customerId, int furnitureId, int quantity);


//    List<Cart> findByCustomerId(Long Id);

//    void delete(int customerId, int furnitureId);
}
