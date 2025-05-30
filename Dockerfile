# Etapa de build
FROM ubuntu:latest AS build

# Instala Maven e JDK
RUN apt-get update && apt-get install -y openjdk-17-jdk maven && apt-get clean

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Etapa final
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/SkyAid-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Se precisar de argumentos para a JVM, use JAVA_OPTS
ENTRYPOINT ["java", "-jar", "app.jar"]
