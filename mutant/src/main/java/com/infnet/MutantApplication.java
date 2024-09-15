package com.infnet;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@EnableDiscoveryClient 
@EnableRabbit
@OpenAPIDefinition(tags = { @Tag(name = "Mutant API", description = "API to register, update, delete and get mutants") })
public class MutantApplication {
    public static void main(String[] args) {
        SpringApplication.run(MutantApplication.class, args);
    }
}