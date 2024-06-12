package com.CMS.config;

import com.CMS.security.JwtAuthenticationFilter;
import com.CMS.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register", "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.POST, "/courses/**").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/courses/**").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/courses/**").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.POST, "/courses/**/lessons").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/courses/lessons/**").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/courses/lessons/**").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/users/{id}/progress").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/users/{id}/progress").hasRole("TEACHER")
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
