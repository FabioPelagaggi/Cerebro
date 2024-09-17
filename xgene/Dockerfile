FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/xgene-1.0-SNAPSHOT.jar /app/xgene.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "xgene.jar"]

# docker build -t xgene-app .
# docker run -p 8082:8082 xgene-app