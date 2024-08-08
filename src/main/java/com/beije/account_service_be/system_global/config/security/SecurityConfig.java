package com.beije.account_service_be.system_global.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
//                .httpBasic(Customizer.withDefaults())
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(restAuthenticationEntryPoint)) // Handle auth errors
                .httpBasic( basic -> basic.authenticationEntryPoint(restAuthenticationEntryPoint))
                .exceptionHandling(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable) // For Postman
                .headers(headers ->  headers.frameOptions(Customizer.withDefaults()).disable()) // For the H2 console
                .authorizeHttpRequests(auth -> auth  // manage access
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll() // NOTE: Spring Security RequestMatchers ignores app. properties server.servlet.context-path=/api
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessions -> sessions
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // chooses password encoder implementation on runtime based on the password's prefix.
    }

}
