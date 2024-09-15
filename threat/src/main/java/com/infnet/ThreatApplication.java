package com.infnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "Threat Level API", version = "1.0", description = "This API checks the threat level of mutants, and if found to be a threat, sends a notification."))
public class ThreatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreatApplication.class, args);
    }
}