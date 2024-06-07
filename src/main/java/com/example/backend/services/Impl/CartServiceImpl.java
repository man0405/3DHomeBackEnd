package com.example.backend.services.Impl;

import com.example.backend.models.entity.Cart;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.FurnitureRepo;
import com.example.backend.services.CartService;
import com.example.backend.services.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    private CustomerService customerService;
    private FurnitureRepo furnitureRepo;

    @Transactional
    @Override

    public void save(int customerId, int furnitureId, int quantity) {
        log.info("Cart is 0.1 " );
        log.info("Furniture " + furnitureId + " is " + quantity);
        Optional<Cart> cart = cartRepository.findCart(customerId, furnitureId);
        log.info("Cart is 0.5 " + cart.isPresent());
        Cart theCart;

        if(cart.isPresent()){
            log.info("Cart is 0 ");
            theCart = cart.get();
            System.out.println("theCart " + theCart.getId());
            theCart.setQuantity(quantity);
        }else {
            log.info("Cart is 1" );
            var theCustomer = customerService.findById((long) customerId);
            var theFurniture = furnitureRepo.findById(Long.valueOf(furnitureId));
            if(quantity > 1){
                log.info("quantity > 1 -" + quantity);
                theCart =  Cart.builder().customer(theCustomer).furniture(theFurniture.get()).quantity(quantity).build();
                log.info("quantity > 1 +" + quantity);
            }
            else{
                log.info("quantity -" + quantity);
                theCart =  Cart.builder().customer(theCustomer).furniture(theFurniture.get()).quantity(1).build();
                log.info("quantity +" + quantity);
            }
        }
        cartRepository.save(theCart);
    }

    @Override
    public List<Cart> findByCustomerId(Long id) {
        return cartRepository.findByCustomer_IdAAndPaid(id);
    }


    @Transactional
    @Override
    public void delete(int customerId, int furnitureId) {
        Optional<Cart> cart = cartRepository.findCartByCustomerIdAndFurnitureId(customerId, furnitureId);
        cartRepository.deleteById(cart.get().getId());
    }
}
