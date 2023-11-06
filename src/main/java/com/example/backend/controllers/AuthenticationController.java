package com.example.backend.controllers;


import com.example.backend.dto.JwtAuthenticationResponse;
import com.example.backend.dto.SignInRequest;
import com.example.backend.dto.SignUpRequest;
import com.example.backend.exception.CustomMessageException;
import com.example.backend.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

//    @PostMapping("/signup")
//    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
//        if(request.getEmail().isBlank() || request.getPassword().isBlank()){
//            throw new CustomMessageException("Your Email or your password mustn't blank" , String.valueOf(HttpStatus.BAD_REQUEST));
//        }
//        return authenticationService.signup(request);
//    }

//    @PostMapping("/signin")
//    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
//        return authenticationService.signin(request);
//    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest request, HttpServletResponse response) {
        if(request.getEmail().isBlank() || request.getPassword().isBlank()){
            throw new CustomMessageException("Your Email or your password mustn't blank" , String.valueOf(HttpStatus.BAD_REQUEST));
        }
        String token = authenticationService.signup(request).getToken();
        cookieSet(response, token);
        return "cookie is added !";
    }

        @PostMapping("/signin")
    public String signin(@RequestBody SignInRequest request, HttpServletResponse response) {
        String token = authenticationService.signin(request).getToken();
            cookieSet(response,token);
        return "cookie is added!";
    }

    private void cookieSet(HttpServletResponse response , String  token){
        Cookie cookie = new Cookie("uss" , token);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);

    }


}