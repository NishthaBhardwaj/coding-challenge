#start with a base image containing Java runtime
FROM openjdk:11-slim as build

#Information around who maintain the image
MAINTAINER nishthasoft

#Add the application's jar to the container
COPY target/api-0.0.1-SNAPSHOT.jar api-0.0.1-SNAPSHOT.jar

#Exceute the application
ENTRYPOINT ["java" , "-jar","/api-0.0.1-SNAPSHOT.jar"]