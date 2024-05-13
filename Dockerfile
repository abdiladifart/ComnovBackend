# Use the official OpenJDK image as a parent image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY target/cominov-backend-0.0.1-SNAPSHOT.jar cominov-backend.jar

# Set the command to run on container startup
ENTRYPOINT ["java", "-jar", "/app/cominov-backend.jar"]
