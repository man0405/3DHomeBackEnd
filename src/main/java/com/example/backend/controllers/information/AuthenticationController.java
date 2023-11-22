package com.example.backend.controllers.information;


import com.example.backend.dto.*;
import com.example.backend.exception.CustomMessageException;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.information.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


import java.util.regex.Pattern;

import static com.example.backend.util.Regex.EMAIL_REGEX;
import static  com.example.backend.util.Regex.PASSWORD_REGEX;

@RestController
@RequestMapping(value= "/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;


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


    @PostMapping(value = "/signup/check")
    public CheckResponse signupCheck(@RequestBody SignUpCheck request){

        CheckAuth(request.getEmail(), request.getPassword());
        return new CheckResponse("GOOD");
    }

    @PostMapping(value = "/signup")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        String token = authenticationService.signup(request).getToken();
        return new JwtAuthenticationResponse(token);
    }


    @PostMapping(value="/signin" )
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        CheckAuth(request.getEmail(), request.getPassword());
        String token = authenticationService.signin(request).getToken();
        return new JwtAuthenticationResponse(token);
    }

    private void CheckAuth (String email, String password){
        if(email.isBlank() || password.isBlank()){
            throw new CustomMessageException("Your Email or your password mustn't blank" , String.valueOf(HttpStatus.BAD_REQUEST));
        }

        if(userRepository.findByEmail(email).isPresent()){
            throw new CustomMessageException("Email already exists", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
        if(!Pattern.matches(EMAIL_REGEX, email)){
            throw new CustomMessageException("Your Email is InValid" , String.valueOf(HttpStatus.BAD_REQUEST));
        }
        if (!Pattern.matches(PASSWORD_REGEX,password)){
            throw new CustomMessageException("Your Password is InValid" , String.valueOf(HttpStatus.BAD_REQUEST));
        }
    }

//    private void cookieSet(HttpServletResponse response , String  token){
//        Cookie cookie = new Cookie("uss" , token);
//        cookie.setSecure(false);
//        cookie.setPath("/");
//        cookie.setHttpOnly(false);
//        cookie.setMaxAge(60*60);
//        response.addCookie(cookie);
//
//    }


}