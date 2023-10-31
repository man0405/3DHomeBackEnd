package com.example.backend.services;

import com.example.backend.models.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        if(newUser.getId() == null){
            newUser.setCreateAt(LocalDateTime.now());
        }
        newUser.setUpdateAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }

}
