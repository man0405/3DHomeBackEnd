package com.example.backend.controllers.information;

import com.example.backend.dto.*;
import com.example.backend.exception.CustomMessageException;


import com.example.backend.services.information.AuthenticationService;
import com.example.backend.services.information.RegistrationService;
import com.example.backend.services.information.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


import java.time.Duration;

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



//    @PostMapping(value="/signin" )
//    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
//        System.out.println(request.getEmail() + request.getPassword());
//
//        CheckAuth(request.getEmail(), request.getPassword());
//        String token = authenticationService.signin(request).getToken();
//        return new JwtAuthenticationResponse(token);
//    }

    @PostMapping(value="/signin" )
    public CheckResponse signin(@RequestBody SignInRequest request , HttpServletResponse response ) {
        System.out.println(request.getEmail() + request.getPassword());

        CheckAuth(request.getEmail(), request.getPassword());
        String token = authenticationService.signin(request).getToken();
//        return new JwtAuthenticationResponse(token);
        response.setHeader(HttpHeaders.SET_COOKIE,cookies(token ,  1).toString() );
        return new CheckResponse("true");
    }


    @GetMapping("/logout")
    public CheckResponse logout(HttpServletResponse response){
        response.setHeader(HttpHeaders.SET_COOKIE,cookies("", 0).toString());
        return new CheckResponse("true");
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


    private ResponseCookie cookies(String token, int hours){
//        Cookie jwtTokenCookie = new Cookie("uss", token);
//        jwtTokenCookie.setMaxAge(60*60);
//        jwtTokenCookie.setSecure(true);
//        jwtTokenCookie.setHttpOnly(true);
//        jwtTokenCookie.setPath("/");
//        jwtTokenCookie.setDomain("http://localhost:3000/");

        ResponseCookie cookie = ResponseCookie.from("uss", token)
                .httpOnly(true)
                .secure(true)
                .maxAge(Duration.ofHours(hours))
                .sameSite("None")
                .path("/")
                .build();

        return cookie;
    }

}