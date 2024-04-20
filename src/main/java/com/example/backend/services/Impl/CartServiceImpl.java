package com.example.backend.services.Impl;

import com.example.backend.models.entity.Cart;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.FurnitureRepository;
import com.example.backend.services.CartService;
import com.example.backend.services.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private CustomerService customerService;
    private FurnitureRepository furnitureRepository;

    @Transactional
    @Override
    public void save(int customerId, int furnitureId, int quantity) {
        Optional<Cart> cart = cartRepository.findCartByCustomerIdAndFurnitureId(customerId, furnitureId);
        Cart theCart;
        if(cart.isPresent()){
            theCart = cart.get();
            theCart.setQuantity(quantity);
        }else {
            var theCustomer = customerService.findById((long) customerId);
            var theFurniture = furnitureRepository.findById(furnitureId);
            theCart =  Cart.builder().customer(theCustomer).furniture(theFurniture).quantity(1).build();
        }
        cartRepository.save(theCart);
    }


    @Transactional
    @Override
    public void delete(int customerId, int furnitureId) {
        Optional<Cart> cart = cartRepository.findCartByCustomerIdAndFurnitureId(customerId, furnitureId);
        cartRepository.deleteById(cart.get().getId());
    }
}
