# Use a Java 11 base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/product-service-0.0.1-SNAPSHOT.jar /app/product-service-0.0.1-SNAPSHOT.jar

# Expose the port your Java application is listening on (assuming it's 8080)
EXPOSE 8080

# Command to run the Java application
CMD ["java", "-jar", "product-service-0.0.1-SNAPSHOT.jar"]