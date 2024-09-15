package com.infnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Threat API", version = "1.0", description = "Threat API Information"))
@EnableDiscoveryClient
public class ThreatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreatApplication.class, args);
    }
}