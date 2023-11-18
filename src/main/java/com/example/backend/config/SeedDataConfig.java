package com.example.backend.config;


import com.example.backend.models.Role;
import com.example.backend.models.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.information.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User admin = User
                    .builder()
                    .firstName("admin")
                    .lastName("admin")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("Man@12345"))
                    .role(Role.ROLE_ADMIN)
                    .build();

            userService.save(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }

}