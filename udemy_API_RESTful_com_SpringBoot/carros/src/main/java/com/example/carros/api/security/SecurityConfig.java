package com.example.carros.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                    .anyRequest().authenticated()
            )
                .csrf().disable()
            .httpBasic(withDefaults());
    return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(User.withUsername("employee").password(passwordEncoder().encode("123456"))
            .roles("EMPLOYEE", "USER").build());

        userDetailsList.add(User.withUsername("manager").password(passwordEncoder().encode("123456"))
            .roles("MANAGER", "USER").build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
