package com.example.backend.controllers.information;


import com.example.backend.services.information.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/test")
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class TestController {


    @GetMapping("/anon")
    public String anonEndPoint() {
        return "everyone can see this";
    }

    @GetMapping("/users")
    public String usersEndPoint() {
        return "ONLY users can see this";
    }

    @GetMapping("/admins")
    public String adminsEndPoint() {
        return "ONLY admins can see this";
    }

    @GetMapping("/verify")
    public String verifyToken(){
        return "Oke";
    }

}
