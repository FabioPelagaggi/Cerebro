package com.infnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "X-Gene API", version = "1.0", description = "This API checks if a given subject is a mutant."))
public class XgeneApplication {
    public static void main(String[] args) {
        SpringApplication.run(XgeneApplication.class, args);
    }
}