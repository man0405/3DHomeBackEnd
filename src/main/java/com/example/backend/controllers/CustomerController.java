package com.example.backend.controllers;

import com.example.backend.dto.CustomerPassword;
import com.example.backend.dto.CustomerPhone;
import com.example.backend.dto.CustomerProfile;
import com.example.backend.services.CustomerService;
import com.example.backend.services.information.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/customer/")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    JwtService jwtService;



    @PutMapping(value = "/profile")
    public CustomerProfile updateProfile(@RequestBody CustomerProfile customerProfile, @RequestHeader("Authorization") String bear){
        customerProfile.setId(getID(bear));
        return customerService.updateProfile(customerProfile);
    }
    @PutMapping(value = "/phone")
    public CustomerPhone updatePhone(@RequestBody CustomerPhone customerPhone, @RequestHeader("Authorization") String bear){
        customerPhone.setId(getID(bear));
        return customerService.updatePhone(customerPhone);
    }
    @PutMapping(value = "/password")
    public CustomerPassword updatePassword(@RequestBody CustomerPassword customerPassword, @RequestHeader("Authorization") String bear ){
        customerPassword.setId(getID(bear));
        return customerService.updatePassword(customerPassword);
    }
    private Long getID(String bear){
        String jwt =  bear.substring(7);
        Long id = Long.parseLong(jwtService.extractId(jwt));
        return id;
    }

}
