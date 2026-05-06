# 1. Provide the Java runtime environment
FROM eclipse-temurin:17-jre-alpine

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the compiled JAR file from your computer into the container
COPY target/*.jar app.jar

# 4. Document the port Spring Boot uses
EXPOSE 8080

# 5. Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]