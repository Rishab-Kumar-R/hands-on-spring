package com.rishab.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class TestSecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequest -> authorizeRequest
                .requestMatchers(HttpMethod.GET, "/api/books").hasRole(Roles.USER.name())
                .requestMatchers(HttpMethod.GET, "/api/products").hasRole(Roles.USER.name())
                .requestMatchers(HttpMethod.GET, "/api/books/**").hasRole(Roles.USER.name())
                .requestMatchers(HttpMethod.GET, "/api/products/**").hasRole(Roles.USER.name())
                .requestMatchers(HttpMethod.POST, "/api/books").hasRole(Roles.MANAGER.name())
                .requestMatchers(HttpMethod.POST, "/api/products").hasRole(Roles.MANAGER.name())
                .requestMatchers(HttpMethod.PUT, "/api/books").hasRole(Roles.MANAGER.name())
                .requestMatchers(HttpMethod.PUT, "/api/products").hasRole(Roles.MANAGER.name())
                .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole(Roles.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole(Roles.ADMIN.name()))
            .httpBasic(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
