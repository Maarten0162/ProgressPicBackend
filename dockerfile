# ==========================================
# STAGE 1: The Builder (Compiling the code)
# ==========================================
# We start with an image that has Maven and Java 17 installed
FROM maven:3.8.5-openjdk-17 AS builder

# Set the working directory for the build process
WORKDIR /build

# Copy ALL your raw source code and pom.xml into the builder container
COPY . .

# Run Maven to compile the code and create the .jar file
# We skip tests here to make the cloud deployment faster
RUN mvn clean package -DskipTests

# ==========================================
# STAGE 2: The Runner (Running the app)
# ==========================================
# Now we start fresh with a tiny image that ONLY has Java (no Maven)
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# We copy the finished .jar file FROM the "builder" stage above
COPY --from=builder /build/target/*.jar app.jar

# Expose port 8080 (Render looks for this to know how to route web traffic)
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]