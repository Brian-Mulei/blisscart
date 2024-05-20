package com.mulei.blisscart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig   {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/register", "/register/admin", "/add/admin", "/login").permitAll()
                    .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
              .requestMatchers("/vendor/**").hasAuthority("ROLE_VENDOR")
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }

//    @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

}
