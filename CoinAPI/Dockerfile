# Stage 1: Build stage (optional if you want Docker to build from Maven)
# Uncomment if you want Docker to build Maven package for you:
#
# FROM maven:3.8.7-eclipse-temurin-17 AS build
# COPY pom.xml .
# COPY src ./src
# RUN mvn clean package

# Stage 2: Runtime stage
FROM eclipse-temurin:17-jre

# Create a working directory
WORKDIR /app

# Copy fat jar and config.yml from local build context
# If you built outside Docker, copy from your local target/
COPY target/CoinAPI-1.0-SNAPSHOT.jar /app/CoinAPI.jar
COPY config.yml /app/config.yml

# Expose the application port
EXPOSE 8080

# Run the Dropwizard application
CMD ["java", "-jar", "CoinAPI.jar", "server", "config.yml"]