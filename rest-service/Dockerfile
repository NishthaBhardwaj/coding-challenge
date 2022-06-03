FROM openjdk:11-slim as build
VOLUME /tmp
EXPOSE 9000
ADD target/backend-coding-challenge-1.0-SNAPSHOT.jar backend-coding-challenge-1.0-SNAPSHOT.jar
ENTRYPOINT [ "sh" ,"-c" , "java -jar /backend-coding-challenge-1.0-SNAPSHOT.jar" ]