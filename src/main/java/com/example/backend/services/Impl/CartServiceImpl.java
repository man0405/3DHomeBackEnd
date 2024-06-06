package com.example.backend.services.Impl;

import com.example.backend.dto.CartResponse;
import com.example.backend.models.entity.Cart;
import com.example.backend.models.entity.Customer;
import com.example.backend.models.entity.Furniture;
import com.example.backend.models.entity.Item;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.FurnitureRepo;
import com.example.backend.repository.ItemRepo;
import com.example.backend.services.CartService;
import com.example.backend.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final ItemRepo itemRepo;
    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final FurnitureRepo furnitureRepo;


    @Override
    @Transactional
    public void addItem(int customerId, int furnitureId, int quantity) {
        Customer customer = customerService.findById(Long.valueOf(customerId));
        if (customer == null)
            throw new EntityNotFoundException("Customer not found");

        Optional<Furniture> furniture = furnitureRepo.findById(Long.valueOf(furnitureId));
        if (furniture.isEmpty())
            throw new EntityNotFoundException("Furniture not found");

        Optional<Item> item = itemRepo.findByCustomerIdAndFurnitureId(Long.valueOf(customerId), Long.valueOf(furnitureId));

        System.out.println("found item: " + item.isPresent());
        Item newItem;
        if (item.isEmpty()){
            newItem = Item.builder().quantity(quantity).localDate(LocalDate.now()).furniture(furniture.get()).customer(customer).build();
            System.out.println(newItem);
        }
        else {
            newItem = item.get();
            newItem.setQuantity(item.get().getQuantity() + quantity);
        }
        itemRepo.save(newItem);

        Optional<Cart> optionalCart = cartRepository.findCartByCustomerId(Long.valueOf(customerId));
        Cart currentCart;
        boolean itemUpdated = false;

        if (optionalCart.isEmpty()) {
            currentCart = Cart.builder().customer(customer).build();
            currentCart.addItem(newItem);
            System.out.println("New cart created and item added to the cart");

        } else {
            currentCart = optionalCart.get();
            for (Item item1 : currentCart.getItems()) {
                if (item1.getFurniture().equals(furniture.get())) {
                    item1.setLocalDate(LocalDate.now());
                    item1.setQuantity(item1.getQuantity() + quantity);
                    itemUpdated = true;
                    System.out.println("Existing item updated in the cart");
                    break;
                }
            }

            if (!itemUpdated) {
                try{
                    currentCart.addItem(newItem);
                }catch (Exception e){
                    System.out.println("Error while adding new item to the cart");
                }

                System.out.println("New item added to the existing cart");
            }
        }

            cartRepository.save(currentCart);
            System.out.println("cart id: "+currentCart.getId());
            System.out.println("customer id: "+newItem.getCustomer().getId());
    }

    @Override
    public CartResponse getCart(int customerId) {
        Cart theCart = cartRepository.findCartByCustomerId(Long.valueOf(customerId)).get();
        return new CartResponse(Math.toIntExact(theCart.getId()), theCart.getItems());
    }


//    @Transactional
//    @Override
//    public void save(int customerId, int furnitureId, int quantity) {
//        Optional<Cart> cart = cartRepository.findCartByCustomerIdAndFurnitureId(customerId, furnitureId);
//        Cart theCart;
//        if(cart.isPresent()){
//            theCart = cart.get();
//            theCart.setQuantity(quantity);
//        }else {
//            var theCustomer = customerService.findById((long) customerId);
//            var theFurniture = furnitureRepo.findById(Long.valueOf(furnitureId));
//            if(quantity > 1) theCart =  Cart.builder().customer(theCustomer).furniture(theFurniture.get()).quantity(quantity).build();
//            else theCart =  Cart.builder().customer(theCustomer).furniture(theFurniture.get()).quantity(1).build();
//        }
//        cartRepository.save(theCart);
//    }




//    @Transactional
//    @Override
//    public void delete(int customerId, int furnitureId) {
//        Optional<Cart> cart = cartRepository.findCartByCustomerIdAndFurnitureId(customerId, furnitureId);
//        cartRepository.deleteById(cart.get().getId());
//    }
}
