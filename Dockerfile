# Start with a base image containing the Java 17 runtime
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/photoservice-0.0.1-SNAPSHOT.jar photoservice.jar

# Expose the port that the Spring Boot application listens on
EXPOSE 8081

# Set the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "photoservice.jar"]