package com.example.backend.services.Impl;

import com.example.backend.models.entity.Cart;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.FurnitureRepo;
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
    private FurnitureRepo furnitureRepo;

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
            var theFurniture = furnitureRepo.findById(Long.valueOf(furnitureId));
            theCart =  Cart.builder().customer(theCustomer).furniture(theFurniture.get()).quantity(1).build();
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
