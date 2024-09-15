FROM openjdk:17-jdk-slim

VOLUME /tmp

COPY target/mutant-1.0-SNAPSHOT.jar mutant-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "mutant-1.0-SNAPSHOT.jar"]