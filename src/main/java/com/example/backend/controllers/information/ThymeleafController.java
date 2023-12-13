package com.example.backend.controllers.information;

import com.example.backend.services.information.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class ThymeleafController {
    private final RegistrationService registrationService;

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token , Model theModel){
        String result;
        try{
            result = registrationService.verifyToken(token);
        }catch (IllegalStateException e ){
            result = e.getMessage();
        }
        theModel.addAttribute("result", result);


        //TODO create Thymeleaf for Confirm token

        return "Confirm";
    }
}
