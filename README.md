# Cerebro Project - Microservices Documentation

## Overview

The **Cerebro Project** is a Maven multi-module microservices architecture, designed to manage mutant-related data. It features four microservices that communicate with each other using Spring Cloud Gateway, Eureka for service discovery, RabbitMQ for messaging, and Swagger for API documentation. The entire system is containerized using Docker.

### Technologies
- **Maven Multi-Module**: Manages multiple microservices as individual modules.
- **Dependency Management**: Ensures each module has correct dependencies.
- **Spring Cloud Gateway**: API Gateway (Port: 8888).
- **Eureka Server**: Service registry and discovery (Port: 8761).
- **RabbitMQ**: Messaging system (Port: 15672).
- **Swagger**: API documentation and testing.
- **Docker**: Containerizes databases and RabbitMQ for easy setup and deployment.

### Microservices Overview

1. **Mutant Service (Port: 8080, Gateway: `/mutants`)**
   - Stores mutant information.
   - Checks if a subject is a mutant using the X-Gene Service before storing it.
   - Receives RabbitMQ notifications from the Threat Level Service about mutants classified as a threat.

2. **X-Gene Service (Port: 8082, Gateway: `/xgene`)**
   - Determines if a subject is a mutant or not.
   - Records all subjects checked in the database.

3. **Threat Level Service (Port: 8088, Gateway: `/threats`)**
   - Evaluates the threat level of mutants.
   - Sends RabbitMQ notifications to Mutant Service if a mutant is deemed a threat.

4. **History Service (Port: 8081, Gateway: `/histories`)**
   - Records and stores every change made to the mutants' database.

### Docker Setup

Docker is used to create and manage the following containers:
1. **Mutants Database**: Stores mutant information.
2. **History Database**: Logs all changes in mutant information.
3. **X-Gene Database**: Stores subjects checked by the X-Gene service.
4. **RabbitMQ Management Tool**: Manages message queues for inter-service communication.

## Microservices Endpoints

### Mutant Service
Base URL: `/mutants`
- **GET** `/api/mutants/{id}`: Fetch mutant info by ID.
- **PUT** `/api/mutants/{id}`: Update a mutant’s info by ID.
- **DELETE** `/api/mutants/{id}`: Delete a mutant by ID.
- **POST** `/api/mutants`: Register a new mutant.
- **GET** `/api/mutants/all`: Retrieve all mutants in the database.

### X-Gene Service
Base URL: `/xgene`
- **GET** `/api/xgene/{mutantId}`: Check if a subject with a given ID is a mutant.
- **GET** `/api/xgene/all`: Return all subjects that have been checked.

### Threat Level Service
Base URL: `/threats`
- **GET** `/api/threats`: Analyze all mutants and assess their threat level. Sends RabbitMQ notifications for dangerous mutants.
- **GET** `/api/threats/{id}`: Check if a specific mutant is a threat.

### History Service
Base URL: `/histories`
- **POST** `/api/histories`: Log a change made in the Cerebro database.
- **GET** `/api/histories/all`: Retrieve all history records.

## Project Structure
This project follows a Maven multi-module structure with the following modules:

```
/cerebro-project
  |-- /cerebro-gateway (Spring Cloud Gateway)
  |-- /eureka-server (Service Discovery)
  |-- /mutant-service
  |-- /xgene-service
  |-- /threat-service
  |-- /history-service
  |-- /common-libraries (Shared utility classes)
```

## Prerequisites

Before starting the project, ensure you have the following installed:
- Java 17+
- Maven 3.6+
- Docker
- RabbitMQ (Docker container will be used)
- Swagger (already integrated in each service for API documentation)

## Running the Project

### 1. Clone the Repository
```bash
git clone https://github.com/FabioPelagaggi/Cerebro.git
cd cerebro
```

### 2. Start Eureka Server
```bash
cd eureka-server
mvn spring-boot:run
```

### 3. Build and Run Microservices
Each microservice can be built and run separately. For example:

```bash
cd mutant-service
mvn spring-boot:run
```

Repeat this for all microservices (`xgene-service`, `threat-service`, `history-service`).

### 4. Start Docker Containers
Docker containers are used for databases and RabbitMQ. To start them, use the provided `docker-compose.yml` file:
```bash
docker-compose up
```

This will set up the required containers:
- Mutants Database
- History Database
- X-Gene Database
- RabbitMQ Management Tool (Port: 15672)

### 5. Access Services

- **Gateway**: `http://localhost:8888`
- **Eureka Dashboard**: `http://localhost:8761`
- **RabbitMQ Management Tool**: `http://localhost:15672`
- **Swagger**: `http://localhost:{port}/swagger-ui.html`

For example:
- **Mutant Service Swagger**: `http://localhost:8080/swagger-ui.html`

### 6. Inter-Service Communication
- **RabbitMQ**: Threat Level Service sends notifications to Mutant Service if a mutant is considered a threat.
- **Eureka**: All services register with the Eureka server for discovery and communication.