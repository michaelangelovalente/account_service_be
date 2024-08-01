package com.beije.account_service_be.system_global.config.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
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
                .csrf(AbstractHttpConfigurer::disable) // used for POSTMAN requests
                .exceptionHandling(ex -> ex.authenticationEntryPoint(restAuthenticationEntryPoint)) // used to handle authentication errors
                .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable()) // For the H2 console
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( auth -> auth // manage access
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll() // NOTE: Spring Security RequestMatchers ignores app. properties server.servlet.context-path=/api
                                .requestMatchers(HttpMethod.POST, "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement( sessions -> sessions
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session
                );

        return http.build();
    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .httpBasic(Customizer.withDefaults())
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(restAuthenticationEntryPoint)) // Handle auth errors
//                .csrf(csrf -> csrf.disable()) // For Postman
//                .headers(headers -> headers.frameOptions().disable()) // For the H2 console
//                .authorizeHttpRequests(auth -> auth  // manage access
//                                .requestMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
//                                .anyRequest().authenticated()
//                        // other matchers
//                )
//                .sessionManagement(sessions -> sessions
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session
//                );
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // chooses password encoder implementation on runtime based on the password's prefix.
    }

}
