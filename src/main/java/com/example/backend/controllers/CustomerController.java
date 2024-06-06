package com.example.backend.controllers;

import com.example.backend.dto.*;
import com.example.backend.models.entity.Cart;
import com.example.backend.models.entity.Customer;
import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Invoice;
import com.example.backend.services.CartService;
import com.example.backend.services.CustomerService;
import com.example.backend.services.InvoiceService;
import com.example.backend.services.VisitService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.hibernate.engine.spi.VersionValue;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backend.util.ExtractId.ExtractIdFromToken;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class CustomerController {

    private final
    CustomerService customerService;

    private final VisitService visitService;

    private final CartService cartService;

    private final InvoiceService invoiceService;


    @GetMapping(value ="info" )
    public InfoResponse getProfile(@RequestHeader("Authorization") String cookie ){

        Customer customer = customerService.findById(ExtractIdFromToken(cookie));

        return InfoResponse
                .builder()
                .phone(customer.getPhone())
                .name(customer.getFirstName() +" "+  customer.getLastName())
                .dob(customer.getDob())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .country(customer.getCountry())
                .build();
    }

    @GetMapping("seen/{offset}")
    public CustomPage<House> getSeenProject(@RequestHeader("Authorization") String cookie, @PathVariable int offset){
        Page<House> housesPage = visitService.findSeenHouse(offset,5, Math.toIntExact((ExtractIdFromToken(cookie))));
        return new CustomPage<>(housesPage);
    }

    @GetMapping("fav/{offset}")
    public APIResponse<Page<HouseResponse>> getLikedHouse(@RequestHeader("Authorization") String cookie, @PathVariable int offset){
        Page<HouseResponse> housesPage = visitService.likedHouse(offset,5, Math.toIntExact((ExtractIdFromToken(cookie))));
        return new APIResponse<>(housesPage.getSize(), housesPage);
    }

    @GetMapping("favFurniture/{offset}")
    public APIResponse<Page<FurnitureResponse>> getLikedFurniture(@RequestHeader("Authorization") String cookie, @PathVariable int offset){
        Page<FurnitureResponse> furniturePage = visitService.likedFurniture(offset,5, Math.toIntExact((ExtractIdFromToken(cookie))));
        return new APIResponse<>(furniturePage.getSize(), furniturePage);
    }

    



    @PutMapping(value = "profile")
    public CustomerProfile updateProfile(@RequestBody CustomerProfile customerProfile, @RequestHeader("Authorization") String cookie){
        System.out.println("Cookie: "  + cookie);
//        customerProfile.setId(getID(cookie));
        customerProfile.setId(ExtractIdFromToken(cookie));
        return customerService.updateProfile(customerProfile);
    }



    @PutMapping(value = "phone")
    public CustomerPhone updatePhone(@RequestBody CustomerPhone customerPhone, @RequestHeader("Authorization") String cookie){
        customerPhone.setId(ExtractIdFromToken(cookie));
        return customerService.updatePhone(customerPhone);
    }

    @PutMapping(value = "password")
    public CustomerPassword updatePassword(@RequestBody CustomerPassword customerPassword, @RequestHeader("Authorization") String cookie){
        customerPassword.setId(ExtractIdFromToken(cookie));
        System.out.println("customer password" + customerPassword);
        return customerService.updatePassword(customerPassword);
    }


//    @PutMapping(value = "addToCart")
//    public CheckResponse addToCart(@RequestHeader("Authorization") String customerId, @RequestParam("furnitureId") int furnitureId, @RequestParam("quantity") int quantity){
//        Long theCustomerId = ExtractIdFromToken(customerId);
//        cartService.save( theCustomerId.intValue(), furnitureId, quantity);
//        return CheckResponse.builder().result("TRUE").build();
//    }

//    @DeleteMapping(value = "removeFromCart")
//    public CheckResponse removeFromCart(@RequestHeader("Authorization") String customerId, @RequestParam("furnitureId") int furnitureId){
//        Long theCustomerId = ExtractIdFromToken(customerId);
//        cartService.delete( theCustomerId.intValue(), furnitureId);
//        return CheckResponse.builder().result("TRUE").build();
//    }


//    @PutMapping(value = "succeed")
//    public Invoice succeedToOrder(@RequestParam("cart")Integer[] carts, @RequestHeader("Authorization") String customerId){
//        Long theCustomerId = ExtractIdFromToken(customerId);
//        return  invoiceService.succeed(carts, theCustomerId );
//    }
    @GetMapping(value = "getCart")
    public CartResponse getCart(@RequestHeader("Authorization") String customerId){
        Long theCustomerId = ExtractIdFromToken(customerId);
        return cartService.getCart(Math.toIntExact(theCustomerId));
    }

    @PutMapping(value = "cart/{furniture}/{quantity}")
    public void addToCard(@RequestHeader("Authorization") String customerId, @PathVariable int furniture, @PathVariable int quantity){
        Long theCustomerId = ExtractIdFromToken(customerId);
        cartService.addItem(Math.toIntExact(theCustomerId), furniture, quantity);
    }


    @GetMapping(value = "getInvoice/{id}")
    public Invoice getInvoice(@PathVariable Long id){
        return invoiceService.getInvoice(id);
    }

//    @GetMapping(value = "getAllInvoice")
//    public List<Invoice> getAllInvoice(@RequestHeader("Authorization") String customerId){
//        Long theCustomerId = ExtractIdFromToken(customerId);
//        return invoiceService.getAllInvoice(theCustomerId);
//    }




}
