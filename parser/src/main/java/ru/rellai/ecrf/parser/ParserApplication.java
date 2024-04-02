package ru.rellai.ecrf.parser;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static org.springframework.boot.WebApplicationType.SERVLET;

@SpringBootApplication
@EnableDiscoveryClient
public class ParserApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ParserApplication.class).web(SERVLET).run(args);
		}

}
