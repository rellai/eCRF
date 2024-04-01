package ru.rellai.ecrf.parser.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@ConfigurationProperties(prefix = "parser")
@Configuration
@EnableAutoConfiguration
@Getter
public class StorageProperties implements FileStorageProperties {

    private String uploadDir;

    private String templateCrfName;

}
