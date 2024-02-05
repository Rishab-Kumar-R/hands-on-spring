package com.example.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.lang.NonNull;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

/*
 * CORS (Cross-Origin Resource Sharing)
 * It is a security feature implemented by browsers to restrict JavaScript code from making requests to a different domain than the one that served the JavaScript code.
 *
 * **Preventing CORS**
 * 1. Same-Origin Policy (SOP) - It is a security measure implemented by browsers to restrict JavaScript code from making requests to a different domain than the one that served the JavaScript code.
 * 2. Cross-Origin Resource Sharing (CORS) - It is a security feature implemented by browsers to restrict JavaScript code from making requests to a different domain than the one that served the JavaScript code.
 * 3. JSONP (JSON with Padding) - It is a method that allows JSON requests to be made from different domains.
 * 4. Proxy Server - It is a server that acts as an intermediary for requests from clients seeking resources from other servers.
 */

/*
 * **Storing User Credentials**
 * 1. In-memory - Not recommended for production use (set the username and password in the application.properties file)
 * 2. Database - Can use JDBC, JPA, or Spring Data JPA
 * 3. LDAP (Lightweight Directory Access Protocol) - Used for accessing centralized directory services
 */

@Configuration
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class BasicAuthSecurityConfig {

    // Disable CSRF and enable HTTP Basic authentication
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/user").hasRole(UserRole.USER.name())
                .requestMatchers("/admin/**").hasRole(UserRole.ADMIN.name())
                .anyRequest().authenticated());
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }

    // Enable CORS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("your-frontend-url.com");
            }
        };
    }

    // In-memory user credentials
    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails user = User.withUsername("user")
    // .password("{noop}password")
    // .roles(UserRole.USER.name())
    // .build();

    // UserDetails admin = User.withUsername("admin")
    // .password("{noop}password") // {noop} is used to specify that the password is
    // not encoded
    // .roles(UserRole.ADMIN.name())
    // .build();

    // return new InMemoryUserDetailsManager(user, admin);
    // }

    // Database user credentials
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        UserDetails user = User.withUsername("user")
                .password("password")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles(UserRole.USER.name())
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("password")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles(UserRole.ADMIN.name(), UserRole.USER.name())
                .build();

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.createUser(user);
        userDetailsManager.createUser(admin);
        return userDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
