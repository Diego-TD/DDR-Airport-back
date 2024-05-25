FROM openjdk:17-jdk-slim
LABEL authors="Diego-TD"

WORKDIR /app

# Copy the Gradle wrapper files and build script
COPY gradlew .
COPY gradle ./gradle

# Copy the build configuration
COPY build.gradle .

# Copy the project source code
COPY . .

# Grant execute permissions to the Gradle wrapper script
RUN chmod +x gradlew

# Download and cache Gradle dependencies
RUN ./gradlew --no-daemon dependencies

# Build the project
RUN ./gradlew --no-daemon build

# Copy the JAR file from the build output directory to the container
COPY build/libs/DDR-Airport-back-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]