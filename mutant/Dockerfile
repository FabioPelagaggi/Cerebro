FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/mutant-1.0-SNAPSHOT.jar /app/mutant.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "mutant.jar"]

# docker build -t mutant-app .
# docker run -p 8080:8080 mutant-app
