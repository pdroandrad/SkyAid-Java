# Build com Maven + JDK 21
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime JDK 21 slim
FROM eclipse-temurin:21-jdk-jammy

EXPOSE 8080
WORKDIR /app

COPY --from=build /app/target/SkyAid-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
