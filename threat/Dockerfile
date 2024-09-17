FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/threat-1.0-SNAPSHOT.jar /app/threat.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "threat.jar"]

# docker build -t threat-app .
# docker run -p 8088:8088 threat-app