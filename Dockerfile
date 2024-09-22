#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER eazybytes.com

# Add the application's jar to the image
COPY target/codingtest-0.0.1-SNAPSHOT.jar codingtest-0.0.1-SNAPSHOT.jar

EXPOSE 8080

# execute the application
ENTRYPOINT ["java", "-jar", "codingtest-0.0.1-SNAPSHOT.jar"]