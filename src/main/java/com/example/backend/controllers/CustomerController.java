package com.example.backend.controllers;

import com.example.backend.dto.CustomerPassword;
import com.example.backend.dto.CustomerPhone;
import com.example.backend.dto.CustomerProfile;
import com.example.backend.services.CustomerService;
import com.example.backend.services.information.JwtService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    JwtService jwtService;



    @PutMapping(value = "api/v1/profile")
    public CustomerProfile updateProfile(@RequestBody CustomerProfile customerProfile, @CookieValue("uss") String cookie){
        System.out.println("Cookie: "  + cookie);
        customerProfile.setId(getID(cookie));
        return customerService.updateProfile(customerProfile);
    }
    @PutMapping(value = "api/v1/phone")
    public CustomerPhone updatePhone(@RequestBody CustomerPhone customerPhone, @CookieValue("uss") String cookie){
        customerPhone.setId(getID(cookie));
        return customerService.updatePhone(customerPhone);
    }
    @PutMapping(value = "api/v1/password")
    public CustomerPassword updatePassword(@RequestBody CustomerPassword customerPassword, @CookieValue("uss") String cookie){
        customerPassword.setId(getID(cookie));
        return customerService.updatePassword(customerPassword);
    }
    private Long getID(String bear){
//        String jwt =  bear.substring(7);
        Long id = Long.parseLong(jwtService.extractId(bear));
        return id;
    }

}
