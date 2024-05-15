package com.mulei.blisscart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    // private final UserService userService;
    // private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new UserService();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/index").hasAuthority("USERS")
                        .requestMatchers("/register", "/add", "/register/admin", "/add/admin").permitAll()
                        .anyRequest().permitAll());
        return httpSecurity.build();
    }

}
