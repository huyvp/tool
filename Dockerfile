# Stage build
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn package -DskipTests --no-transfer-progress && rm -rf /root/.m2/repository

# Stage create image
FROM --platform=amd64 openjdk:17.0.2-oraclelinux8
LABEL authors="huyvp"
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]