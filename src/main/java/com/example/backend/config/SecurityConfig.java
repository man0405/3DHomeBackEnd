package com.example.backend.config;


import com.example.backend.exception.CustomLogoutHandler;
import com.example.backend.filters.JwtAuthenticationFilter;
import com.example.backend.exception.OAuth2LoginSuccessHandler;
import com.example.backend.services.information.CustomOAuth2UserService;
import com.example.backend.services.information.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(csrf -> csrf.disable())
//                .cors(Customizer.withDefaults()).
//                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
//                authorizeHttpRequests
//                        (auth -> auth.anyRequest().authenticated())
//                .oauth2Login(Customizer.withDefaults())
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LogoutHandler customLogoutHandler) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**", "/api/test/v1/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/auth/**", "/api/test/v1/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auto-api/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/add-house/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/image/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/image/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/image/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/house/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/oauth/**").authenticated()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .successHandler(oAuth2LoginSuccessHandler)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler(customLogoutHandler)
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "uss")
                );
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000/","http://localhost:3001/"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT" ,"DELETE" , "HEAD" , "OPTIONS"));
            configuration.setAllowedHeaders(List.of("Content-type"));
            configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**" , configuration);
        return source;
    }
}