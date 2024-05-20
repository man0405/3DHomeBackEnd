package com.example.backend.exception;
import com.example.backend.models.entity.User;
import com.example.backend.services.information.JwtService;
import com.example.backend.services.information.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserService userService;

    public OAuth2LoginSuccessHandler(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userService.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        String token = jwtService.generateToken(user);

        response.addHeader("Authorization", "Bearer " + token);
        response.sendRedirect("/");  // Redirect to a default page
    }
}
