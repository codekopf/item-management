# Start with a base image of Java 20
# openjdk:20-jdk-alpine does not exist anymore, using alternative Amazon Corretto
FROM amazoncorretto:20-alpine-jdk

# Set the working directory to /app
WORKDIR /app

# Copy the executable JAR file and any other necessary files from the build context into the container at /app
COPY target/item-management-0.0.1-SNAPSHOT.jar /app/item-management.jar

# Expose port 8080 for the Spring Boot application
EXPOSE 8080

# Run app
CMD ["java", "-jar", "item-management.jar"]
