# ==========================================
# STAGE 1: The Builder 
# ==========================================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# ==========================================
# STAGE 2: The Runner 
# ==========================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]