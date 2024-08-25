# Use the Maven image to build the project
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the source code into the container
COPY pom.xml .
COPY src ./src

# Package the application into a JAR file
RUN mvn clean package -DskipTests

# Use the Eclipse Temurin 17 runtime as a parent image for the final image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /blisscart

# Copy the JAR file from the build stage into the runtime stage
COPY --from=build /blisscart/target/blisscart-0.0.1-SNAPSHOT.jar app.jar

# Make port 8080 available to the outside world
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
