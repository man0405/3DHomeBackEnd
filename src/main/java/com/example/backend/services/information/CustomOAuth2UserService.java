package com.example.backend.services.information;
import com.example.backend.models.Role;
import com.example.backend.models.entity.User;
import com.example.backend.services.information.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getName();
        String email = oAuth2User.getAttribute("email");

        Optional<User> optionalUser = userService.findByEmail(email);
        User user;

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            user = new User();
            user.setEmail(email);
            user.setProvider(provider);
            user.setProviderId(providerId);
            user.setRole(Role.ROLE_CUSTOMER); // Set default role
            user.setEnabled(true);
            userService.save(user);
        }

        return new DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority(user.getRole().name())),
                oAuth2User.getAttributes(),
                "email"
        );
    }
}

