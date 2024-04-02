package ru.rellai.ecrf.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Objects;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;


@RequiredArgsConstructor
@RequestScope(proxyMode = TARGET_CLASS)
@Service
public class JwtToken {

    @Value("${spring.security.oauth2.client.registration.study.client-id}")
    private String oAuthClientId;

    private final OAuth2AuthorizedClientManager authorizedClientManager;


    public String token() {
        var token = Objects.requireNonNull(this.authorizedClientManager.authorize(
                        OAuth2AuthorizeRequest.withClientRegistrationId(oAuthClientId)
                                .principal(SecurityContextHolder.getContext().getAuthentication())
                                .build()))
                .getAccessToken().getTokenValue();
        System.out.println("JWT-токен: " + token);
        return token;
    }

}
