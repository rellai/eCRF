package ru.rellai.ecrf.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/webjars/**", "/styles/**", "/js/**");
    }
/*
    @Bean
    @Order(1)
    public SecurityFilterChain nullSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/actuator",
                        "/actuator/**",
                        "/swagger-ui/**",
                        "/api-docs",
                        "/api-docs/**",
                        "/api/**",
                        "/oauth2/**",
                        "/**")
                .authorizeHttpRequests((request) -> request

                        .anyRequest()
                        .permitAll()

                )
        ;

        return http.build();
    }

*/


    // Настройка сервера авторизации
    @Bean
    @Order(2)
    SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(withDefaults());
        return http.formLogin(withDefaults()).build();
    }



    // Настройка доступа к веб приложению
    @Bean
    @Order(3)
    public SecurityFilterChain firstSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(
                            "/login",
                            "/actuator",
                            "/actuator/**",
                            "/swagger-ui/**",
                            "/api-docs",
                            "/api-docs/**",
                            "/api/**",
                            "/oauth2/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(fL -> fL.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/"))
                        .logout(Customizer.withDefaults())
                        .rememberMe(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    // Добавляем роли в токены
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        return context -> {
            if (context.getTokenType().getValue().equals("access_token") ||
                    context.getTokenType().getValue().equals("id_token")) {
                Authentication authentication = context.getPrincipal();
                List<String> roles = authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());
                Map<String, Object> claims = new HashMap<>();



                // Добавляем роли пользователя как claim
                claims.put("roles", roles);
                context.getClaims().claims(claimsMap -> claimsMap.putAll(claims));
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }



}




