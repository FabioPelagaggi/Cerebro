FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/threat-1.0-SNAPSHOT.jar /app/threat.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "threat.jar"]