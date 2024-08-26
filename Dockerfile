# Use the Maven image with JDK 17 to build and run the application
FROM maven:3.8.7-eclipse-temurin-17

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Package the application into a JAR file
RUN mvn clean package -DskipTests

# Expose port 8080 to the outside world
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/blisscart-0.0.1-SNAPSHOT.jar"]
