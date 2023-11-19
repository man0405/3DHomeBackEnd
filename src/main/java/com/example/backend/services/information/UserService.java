package com.example.backend.services.information;

import com.example.backend.exception.CustomMessageException;
import com.example.backend.models.entity.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
            }
        };
    }

    public User save(User newUser){
        this.userValidation(newUser);
        if(newUser.getId() == null){
            newUser.setCreateAt(LocalDateTime.now());
        }
        newUser.setUpdateAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }

    private void userValidation(User newUser) {
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new CustomMessageException("Email already exists", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

}
