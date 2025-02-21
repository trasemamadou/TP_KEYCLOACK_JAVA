package com.groupeisi.security_keycloak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactiver CSRF si tu n'utilises pas de formulaire sécurisé
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/public").permitAll() // Routes accessibles sans connexion
                        .anyRequest().authenticated() // Toutes les requêtes sont autorisées après authentification
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/keycloak") // Redirection vers Keycloak pour login
                        .defaultSuccessUrl("/products", true) // Redirection après succès
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}
