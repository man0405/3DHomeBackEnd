package com.example.backend.controllers.information;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/")
public class OAuth2Controller {

    @GetMapping("owner")
    public String verifyOwner(){
        return "Oke";
    }
}
