# ==========================================
# STAGE 1: The Builder 
# ==========================================
# Switched to Maven with Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# ==========================================
# STAGE 2: The Runner 
# ==========================================
# Switched to the Java 21 JRE
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]