package com.example.backend.controllers.test;


import com.example.backend.dto.SignUpRequest;
import com.example.backend.services.information.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/test/v1")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody SignUpRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.verifyToken(token);
    }

}
