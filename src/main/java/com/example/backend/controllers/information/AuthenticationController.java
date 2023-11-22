package com.example.backend.controllers.information;

import com.example.backend.dto.*;
import com.example.backend.exception.CustomMessageException;


import com.example.backend.services.information.AuthenticationService;
import com.example.backend.services.information.RegistrationService;
import com.example.backend.services.information.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


import static com.example.backend.util.Regex.CheckMail;
import static com.example.backend.util.Regex.CheckPassword;


@RestController
@RequestMapping(value= "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;
    private final UserService userService;


    @PostMapping(value = "/signup/check")
    public CheckResponse signupCheck(@RequestBody SignUpCheck request){
        userService.userValidation(request.getEmail());
        CheckAuth(request.getEmail(), request.getPassword());
        return new CheckResponse("true");
    }


    @PostMapping(value = "/signup")
    public CheckResponse register(@RequestBody SignUpRequest request){
        registrationService.register(request);
        return new CheckResponse("true");
    }



    @PostMapping(value="/signin" )
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        System.out.println(request.getEmail() + request.getPassword());

        CheckAuth(request.getEmail(), request.getPassword());
        String token = authenticationService.signin(request).getToken();
        return new JwtAuthenticationResponse(token);
    }




    private void CheckAuth (String email, String password){
        if(email.isBlank() || password.isBlank()){
            throw new CustomMessageException("Your Email or your password mustn't blank" , String.valueOf(HttpStatus.BAD_REQUEST));
        }

        if(!CheckMail(email)){
            throw new CustomMessageException("Your Email is InValid" , String.valueOf(HttpStatus.BAD_REQUEST));
        }
        if (!CheckPassword(password)){
            throw new CustomMessageException("Your Password is InValid" , String.valueOf(HttpStatus.BAD_REQUEST));
        }
    }



}