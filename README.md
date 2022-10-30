# Ekar Service

### This is a simple application that demonstrates Java conucrrency in a Spring Boot environment.

#### There 2 ways in which you can use this code repository:

  
  1): you can download the ekar-service code repository, change the spring.jpa.hibernate.ddl-auto to update in the application.properties file in the application directory
  
  2): you can download the ekar-service code repository and simply run the docker-compose.yml file in thr root directory of the project with the following command: `docker-compose up`
  
  
The docker image for the ekar-service has been uploaded to DockerHub so this docker compose file will automtically pull the requisite images from DockerHub and start running them on your local host machine. You may view the docker image by clinking on [Docker Image Link](https://hub.docker.com/repository/docker/raffey/ekar-service)


The following images show the docker image being created and uploaded to DockerHub:

<img width="1422" alt="Screen Shot 2022-10-30 at 11 52 39 AM" src="https://user-images.githubusercontent.com/41152148/198866373-f87597f1-2239-468b-95ef-c63958f35ed0.png">
<img width="1422" alt="Screen Shot 2022-10-30 at 11 53 12 AM" src="https://user-images.githubusercontent.com/41152148/198866383-8fd2728f-7419-4921-a7f9-156b3ad0c8e4.png">
<img width="1421" alt="Screen Shot 2022-10-30 at 11 53 43 AM" src="https://user-images.githubusercontent.com/41152148/198866387-3ddf8eb5-b432-4aa8-a6a0-afbc5ec4d401.png">
<img width="1440" alt="Screen Shot 2022-10-30 at 11 54 05 AM" src="https://user-images.githubusercontent.com/41152148/198866392-c262c273-5ade-4082-a273-ae77dc013186.png">


# Request cURL(s):

Once you the application up and running through either of the 2 ways mentioned earlier, you can use the following cURL(s) on your local host machine to execute the code flows of 
the application:

#### cURL to execute the thread(s) creation of the producer(s) and/or consumer(s):

```
curl --location --request POST 'http://localhost:8081/v1.0/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "producers": 5,
    "consumers": 2
}'
```

#### cURL to execute the counter value reset:

```
curl --location --request POST 'http://localhost:8081/v1.0/change-number?counter=50' \
--data-raw ''
```

***Note that once the counter value has reached 0 or 100, you have to reset the value to any number between 0 - 100 otherwise the request to create thread(s) will not work and will return a response asking you to.***
