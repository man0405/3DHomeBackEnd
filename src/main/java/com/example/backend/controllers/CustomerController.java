package com.example.backend.controllers;

import com.example.backend.dto.CustomerPassword;
import com.example.backend.dto.CustomerPhone;
import com.example.backend.dto.CustomerProfile;
import com.example.backend.dto.InfoResponse;
import com.example.backend.models.entity.Customer;
import com.example.backend.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.util.ExtractId.ExtractIdFromToken;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class CustomerController {

    private final
    CustomerService customerService;
    private PasswordEncoder passwordEncoder;

    @GetMapping(value ="info" )
    public InfoResponse getProfile(@CookieValue("uss") String cookie ){
        Customer customer = customerService.findByUser_Id(ExtractIdFromToken(cookie));
        return InfoResponse
                .builder()
                .phone(customer.getPhone())
                .name(customer.getFirstName() +" "+  customer.getLastName())
                .build();
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
