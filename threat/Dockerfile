# Use a base image with Java installed
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Java module JAR file to the container
COPY target/threat-1.0-SNAPSHOT.jar /app/threat-1.0-SNAPSHOT.jar

# Run the Java module
CMD ["java", "-jar", "threat-1.0-SNAPSHOT.jar"]