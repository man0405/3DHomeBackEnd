package com.example.backend.controllers.information;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verify")
public class VerifyController {

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @GetMapping("owner")
    public String verifyOwner(){
        return "Oke";
    }
}
