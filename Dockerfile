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

RUN chmod +x gradlew

RUN ./gradlew --no-daemon dependencies

RUN ./gradlew --no-daemon build

COPY build/libs/DDR-Airport-back-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]