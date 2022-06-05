# Backend Code Challenge REST API

## Requirements


- [JDK 11](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)


### Building and deploying the application

### Building the application

The project uses [Maven](https://maven.apache.org/) as a build tool. It already contains ./mvnw wrapper script, so there's no need to install maven.

To build the project execute the following command:

```bash
  ./mvnw clean install 
```

### Running the application 
####On Linux or Mac

```bash
  ./mvnw spring-boot:run
```
#### On Windows

```bash
  mvnw.cmd spring-boot:run
```
#### Using Jar file

```bash
  /rest-service/target/  java -jar backend-coding-challenge-1.0-SNAPSHOT.jar
```
### Logs can be observed on following location 

```bash
  /rest-service/logs/ tail -f app.log
```

### Alternative way to run application using docker-compose
To skip all the setting up and building, just execute the following command if you are on Linux or Windows:


```bash
docker-compose up
```

for Mac run the following command

```bash
docker-compose -f docker-compose-mac.yml up -d 
```


This will start the API container exposing the application's port
(set to `9000`).

### Testing Application
In order to test if the application is up, you can call its base url:

```bash
curl http://localhost:9000
```

You should get a response similar to this:

```
{healthy:true}
```



## Swagger URL
http://localhost:9000/swagger-ui.html

###### Clean up the resources
###### If docker-compose is used for deploying the application

Use the following commands to clean up:


```bash
docker-compose rm  
```

```bash
docker-compose  -f docker-compose-mac.yml rm
```

It clears stopped containers correctly. Might consider removing clutter of images too, especially the ones fiddled with:

```bash
docker images

docker image rm <image-id>
```

