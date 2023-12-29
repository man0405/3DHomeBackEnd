package com.example.backend.services.information;


import com.example.backend.dto.JwtAuthenticationResponse;
import com.example.backend.dto.SignInRequest;
import com.example.backend.dto.SignUpRequest;
import com.example.backend.exception.CustomMessageException;
import com.example.backend.models.Role;
import com.example.backend.models.entity.Customer;
import com.example.backend.models.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.CustomerService;
import com.example.backend.services.information.JwtService;
import com.example.backend.services.information.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    public JwtAuthenticationResponse signup(SignUpRequest request) {
//        var user = User
//                .builder()
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.ROLE_USER)
//                .build();
//        var customer = Customer
//                .builder()
//                .phone(request.getPhone())
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .user(user)
//                .build();
//
//        user = userService.save(user);
//        customerService.saveCustomer(customer);
//        System.out.println("customer id :" + customer.getId());
//
//        var jwt = jwtService.generateToken(user, customer);
//        return JwtAuthenticationResponse.builder().token(jwt).build();
//    }
//

    public JwtAuthenticationResponse signin(SignInRequest request) {

//        customerService.
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        } catch (NullPointerException ex){
            throw  new CustomMessageException("The account isn't active. Please check your mail and active account" , String.valueOf(HttpStatus.NOT_ACCEPTABLE));
        }
        catch (Exception ex){
            throw  new CustomMessageException("Your email or password is invalid" , String.valueOf(HttpStatus.UNAUTHORIZED));
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomMessageException("Invalid email or password." , String.valueOf(HttpStatus.BAD_REQUEST.value())));

        var customer = customerService.findByUser_Id(user.getId());
        var jwt = jwtService.generateToken(user,customer);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}