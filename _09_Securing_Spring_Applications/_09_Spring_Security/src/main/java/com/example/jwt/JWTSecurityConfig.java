package com.example.jwt;

import com.example.basic.UserRole;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/*
 * **JWT (JSON Web Token)**
 * It is a standard for creating access tokens that assert some number of claims.
 *
 * **Advantages of JWT**
 * 1. Stateless - The server does not need to store the token
 * 2. Scalable - The server does not need to store the token
 * 3. Decentralized - The token can be verified and trusted without contacting server
 * 4. Cross-Domain - The token can be sent with a request to a different domain
 * 5. Performance - The token is small and can be sent with a request
 *
 * **What does a JWT contain?**
 * 1. Header - Contains the type of token and the signing algorithm (Type: JWT, Algorithm: HS256)
 * 2. Payload - Contains the claims (e.g., username, roles, expiration)
 * 3. Signature - Contains the encoded header, encoded payload, and a secret key
 *
 * **Getting Stated with JWT Security Configuration**
 * * JWT Authentication using Spring Boot's OAuth2 Resource Server
 * 1: Create Key Pair
 *   * We will use java.security.KeyPairGenerator to generate a key pair
 *   * We can also use openssl to generate a key pair
 * 2: Create RSA (Rivest-Shamir-Adleman) Key object Key Pair
 *   * com.nimbusds.jose.jwk.RSAKey
 * 3: Create JWKSource (JSON Web Key Source)
 *   * Create JWKSet (JSON Web Key Set) with the RSA Key object
 *   * Create JWKSource with the JWKSet
 * 4: Use RSA Public Key for Decoding JWT
 *   * NimbusJwtDecoder.withPublicKey(RSAKey.toRSAPublicKey()).build()
 * 5: Use JWKSource for Encoding JWT
 *   * return new NimbusJwsEncoder(jwkSource())
 */

// @Configuration
public class JWTSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

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

    // 1: Create Key Pair
    @Bean
    public KeyPair keyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 2: Create RSA (Rivest-Shamir-Adleman) Key object Key Pair
    @Bean
    public RSAKey rsaKey(KeyPair keyPair) {
        return new RSAKey
            .Builder((RSAPublicKey) keyPair.getPublic())
            .privateKey(keyPair.getPrivate())
            .keyID(UUID.randomUUID().toString())
            .build();
    }

    // 3: Create JWKSource (JSON Web Key Source)
    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    // 4: Use RSA Public Key for Decoding JWT
    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder
            .withPublicKey(rsaKey.toRSAPublicKey())
            .build();
    }

    // 5: Use JWKSource for Encoding JWT
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }
}
