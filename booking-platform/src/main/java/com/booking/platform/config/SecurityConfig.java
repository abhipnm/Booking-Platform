package com.booking.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for APIs + H2
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // allow H2 frames
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // H2 console
                        .requestMatchers("/actuator/**").permitAll()   // health checks
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
