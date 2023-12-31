package com.example.backend.filters;


import java.io.IOException;

import com.example.backend.exception.CustomMessageException;
import com.example.backend.services.information.JwtService;
import com.example.backend.services.information.UserService;
import jakarta.servlet.http.Cookie;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String jwt = null;
        final String userEmail;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("uss") ){
                    System.out.println("uss" + cookie.getValue());
                    jwt = cookie.getValue();
                    break;
                }
            }
        }
//        System.out.println("cookie" + request.getCookies());
        if(StringUtils.isEmpty(jwt)){
            if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
        }
        System.out.println("jwt :" + jwt);

        userEmail = jwtService.extractUserName(jwt);
        log.debug("useEmail - {}" , userEmail);
        if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                log.debug("User - {}", userDetails);
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
                log.debug("done");
            } else {
                throw new CustomMessageException("Unauthorized" , String.valueOf(HttpStatus.UNAUTHORIZED.value()));
            }
        }
        filterChain.doFilter(request, response);
    }
}
