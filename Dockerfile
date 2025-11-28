# Multi-stage Dockerfile for Yarago Billing Service
# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-alpine AS builder

# Install Maven
RUN apk add --no-cache maven

# Set working directory
WORKDIR /build

# Copy parent POM and all module directories (required for multi-module Maven build)
COPY pom.xml .
COPY yarago-common ./yarago-common
COPY yarago-discovery-service ./yarago-discovery-service
COPY yarago-gateway ./yarago-gateway
COPY yarago-auth-service ./yarago-auth-service
COPY yarago-patient-service ./yarago-patient-service
COPY yarago-appointment-service ./yarago-appointment-service
COPY yarago-consultation-service ./yarago-consultation-service
COPY yarago-billing-service ./yarago-billing-service
COPY yarago-notification-service ./yarago-notification-service

# Build only the billing service and its dependencies
RUN mvn clean package -pl yarago-billing-service -am -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jre-alpine

# Add curl for healthcheck
RUN apk add --no-cache curl

# Set working directory
WORKDIR /app

# Copy only the built JAR from builder stage
COPY --from=builder /build/yarago-billing-service/target/yarago-billing-service-*.jar app.jar

# Expose port
EXPOSE 8085

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
