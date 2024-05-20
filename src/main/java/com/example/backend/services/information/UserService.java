package com.example.backend.services.information;

import com.example.backend.exception.CustomMessageException;
import com.example.backend.models.entity.User;
import com.example.backend.models.entity.Verification;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final VerificationService verificationService;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
            }
        };
    }

    public User save(User newUser){
        this.userValidation(newUser.getEmail());
        if(newUser.getId() == null){
            newUser.setCreateAt(LocalDateTime.now());
        }
        newUser.setUpdateAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }

    public String createToken(User user){

        String token = UUID.randomUUID().toString();
        Verification verification = new Verification(token, LocalDateTime.now() , LocalDateTime.now().plusMinutes(15),user);
        verificationService.saveVerificationToken(verification);
        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    public void userValidation(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new CustomMessageException("Email already exists", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
