package com.example.backend.exception;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Clear the JWT cookie
        Cookie jwtCookie = new Cookie("JSESSIONID", null);
        jwtCookie.setPath("/");
        jwtCookie.setHttpOnly(true);
        jwtCookie.setMaxAge(0); // Set max age to 0 to delete the cookie
        response.addCookie(jwtCookie);

        // Add any additional cleanup logic if necessary
    }
}
