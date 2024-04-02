package ru.rellai.ecrf.parser.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "RESTful API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Anton Ivanov", email = "rellai@yandex.ru", url = "rellai.ru"
                ),
                license = @License(
                        name = "GPL v3", url = "https://www.gnu.org/licenses/gpl-3.0.en.html"
                ),
                termsOfService = "",
                description = ""
        ),
        servers = @Server(
                url = "/",
                description = "This server"
        )
)
@SecurityScheme(name = "Authentication OAuth2",
                in = SecuritySchemeIn.COOKIE,
                type = SecuritySchemeType.OAUTH2,
                flows = @OAuthFlows(
                        authorizationCode =
                            @OAuthFlow(authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
                                       tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
                                       refreshUrl = "${springdoc.oAuthFlow.refreshUrl}",
                                       scopes =  {@OAuthScope(name = "openid"), @OAuthScope(name = "study")}
                            )
                        )
                )
public class OpenAPISecurityConfig {
}