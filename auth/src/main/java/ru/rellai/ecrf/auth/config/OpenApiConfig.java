package ru.rellai.ecrf.auth.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // Авторизация с пользователем
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Sample API").version("1.0"))
                .components(new Components()
                        .addSecuritySchemes("OAuthScheme", createOAuthScheme()))
                .addSecurityItem(new SecurityRequirement().addList("OAuthScheme"));
    }

    private SecurityScheme createOAuthScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .description("OAuth2 Authentication")
                .flows(new OAuthFlows().authorizationCode(
                        new OAuthFlow()
                                .authorizationUrl("http://127.0.0.1:9000/oauth2/authorize")
                                .tokenUrl("http://127.0.0.1:9000/oauth2/token")
                                .refreshUrl("http://127.0.0.1:9000/oauth2/refresh")
                                .scopes(new Scopes()
                                        .addString("openid", "Grants OIDC access")
                                )));
    }

}