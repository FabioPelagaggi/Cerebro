FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/history-1.0-SNAPSHOT.jar /app/history.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "history.jar"]