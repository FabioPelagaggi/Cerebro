package com.infnet;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableRabbit
@SpringBootApplication
@EnableDiscoveryClient 
@OpenAPIDefinition(info = @Info(title = "Cerebro Mutant API", version = "1.0", description = "This API, checks if a given subject is a mutant and, if positive, saves the mutant in the Cerebro database."))
public class MutantApplication {
    public static void main(String[] args) {
        SpringApplication.run(MutantApplication.class, args);
    }
}