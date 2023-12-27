package com.example.backend.controllers;

import com.example.backend.dto.*;
import com.example.backend.models.entity.Customer;
import com.example.backend.models.entity.House;
import com.example.backend.services.CustomerService;
import com.example.backend.services.Impl.CustomerServiceImpl;
import com.example.backend.services.Impl.VisitServiceImpl;
import com.example.backend.services.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.util.ExtractId.ExtractIdFromToken;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class CustomerController {

    private final
    CustomerService customerService;
    private final VisitService visitService;

    @GetMapping(value ="info" )
    public InfoResponse getProfile(@CookieValue("uss") String cookie ){

        Customer customer = customerService.findById(ExtractIdFromToken(cookie));
        return InfoResponse
                .builder()
                .phone(customer.getPhone())
                .name(customer.getFirstName() +" "+  customer.getLastName())
                .build();
    }

    @GetMapping("seen/{offset}")
    public CustomPage<House> getSeenProject(@CookieValue("uss") String cookie, @PathVariable int offset){
        Page<House> housesPage = visitService.findSeenHouse(offset,5, Math.toIntExact((ExtractIdFromToken(cookie))));
        return new CustomPage<>(housesPage);
    }



    @PutMapping(value = "profile")
    public CustomerProfile updateProfile(@RequestBody CustomerProfile customerProfile, @CookieValue("uss") String cookie){
        System.out.println("Cookie: "  + cookie);
//        customerProfile.setId(getID(cookie));
        customerProfile.setId(ExtractIdFromToken(cookie));
        return customerService.updateProfile(customerProfile);
    }

    @PutMapping(value = "phone")
    public CustomerPhone updatePhone(@RequestBody CustomerPhone customerPhone, @CookieValue("uss") String cookie){
        customerPhone.setId(ExtractIdFromToken(cookie));
        return customerService.updatePhone(customerPhone);
    }

    @PutMapping(value = "password")
    public CustomerPassword updatePassword(@RequestBody CustomerPassword customerPassword, @CookieValue("uss") String cookie){
        customerPassword.setId(ExtractIdFromToken(cookie));
        System.out.println("customer password" + customerPassword);
        return customerService.updatePassword(customerPassword);
    }
}
