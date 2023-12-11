package com.example.backend.controllers;

import com.example.backend.dto.CustomerPassword;
import com.example.backend.dto.CustomerPhone;
import com.example.backend.dto.CustomerProfile;
import com.example.backend.services.CustomerService;
import com.example.backend.services.information.JwtService;
import com.example.backend.util.ExtractId;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.util.ExtractId.ExtractIdFromToken;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final
    CustomerService customerService;
    private PasswordEncoder passwordEncoder;



    @PutMapping(value = "api/v1/profile")
    public CustomerProfile updateProfile(@RequestBody CustomerProfile customerProfile, @CookieValue("uss") String cookie){
        System.out.println("Cookie: "  + cookie);
//        customerProfile.setId(getID(cookie));
        customerProfile.setId(ExtractIdFromToken(cookie));
        return customerService.updateProfile(customerProfile);
    }
    @PutMapping(value = "api/v1/phone")
    public CustomerPhone updatePhone(@RequestBody CustomerPhone customerPhone, @CookieValue("uss") String cookie){
        customerPhone.setId(ExtractIdFromToken(cookie));
        return customerService.updatePhone(customerPhone);
    }
    @PutMapping(value = "api/v1/password")
    public CustomerPassword updatePassword(@RequestBody CustomerPassword customerPassword, @CookieValue("uss") String cookie){
        customerPassword.setId(ExtractIdFromToken(cookie));
        System.out.println("customer password" + customerPassword);
        return customerService.updatePassword(customerPassword);
    }


}
