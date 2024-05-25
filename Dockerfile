FROM openjdk:17-jdk-slim
LABEL authors="Diego-TD"

WORKDIR /DDR-Airport-back

# Copy the Gradle wrapper files and build script
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the project source code
COPY src ./src

# Run the Gradle build
RUN chmod +x gradlew && ./gradlew clean build
RUN ls -l build/libs
# Copy the JAR file
COPY build/libs/DDR-Airport-back-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]