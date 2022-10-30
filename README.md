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


## Request cURL(s):

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


## Code Flows

If you decide to start the service using the `docker-compose up` command, you will see the following:

<img width="1440" alt="Screen Shot 2022-10-30 at 12 09 24 PM" src="https://user-images.githubusercontent.com/41152148/198866975-f112749e-97a8-4b32-be30-0519050f8a79.png">
<img width="1440" alt="Screen Shot 2022-10-30 at 12 09 30 PM" src="https://user-images.githubusercontent.com/41152148/198866989-98f8a1f7-22fe-4d52-89b7-0f54e4d75180.png">


1) If you execute the cURL which creates the producer and consumer threads, you will see the following:

<img width="1440" alt="Screen Shot 2022-10-30 at 12 13 15 PM" src="https://user-images.githubusercontent.com/41152148/198867085-bb2c73af-9a4b-468f-bfbb-1e917cae8f01.png">
<img width="1440" alt="Screen Shot 2022-10-30 at 12 13 19 PM" src="https://user-images.githubusercontent.com/41152148/198867097-f572c21c-c027-4a07-ad03-074caca13588.png">
<img width="1440" alt="Screen Shot 2022-10-30 at 12 13 27 PM" src="https://user-images.githubusercontent.com/41152148/198867104-3c0e2160-ea6d-44f5-a30a-abba056adfc9.png">


2) If you execute the cURL which creates the producer and consumer threads and again execute the same cURL while modifying the no. of consumers, you will see the following (you may notice the 2nd request having a different no. of producers and consumers which spawns a greater no. of consumers than producers and hence, the no. of consumers manage to decrement the value of the counter to 0 first):

<img width="1440" alt="Screen Shot 2022-10-30 at 12 14 39 PM" src="https://user-images.githubusercontent.com/41152148/198867196-00354d66-c6a6-40d6-94f7-0d5030b408f5.png">
<img width="1440" alt="Screen Shot 2022-10-30 at 12 15 29 PM" src="https://user-images.githubusercontent.com/41152148/198867200-97354592-a3f7-4bae-8a41-a433d5b847fb.png">
<img width="1440" alt="Screen Shot 2022-10-30 at 12 15 33 PM" src="https://user-images.githubusercontent.com/41152148/198867206-06038be6-dedd-4e48-96e9-cc02ecddfff2.png">


In the `ekar_service` database, you will see 2 tables namely: `request_response_log` table responible for logging incoming requests of the 1st cURL that creates producer and consumer threads, and the `therad_timestamp` table which is responsible for logging the thread name which happens to increment or decrement the counter value and the timestamp at which it happens.

<img width="1440" alt="Screen Shot 2022-10-30 at 12 26 57 PM" src="https://user-images.githubusercontent.com/41152148/198867476-05ee3278-42fe-4a3d-b89c-094fd5ff0387.png">

##Conclusion

As this simple app demonstrates how Java concurrency works, there are many ways in which this app could be improved. I'm going to list down a couple of them:

1) use flyway intializer to write SQL migration scripts that will create tables in the MySQL DB. This will help keep a record of the craetion and changes that have taken place at a DB level
2) use a Spring Cloud Config Server which will store the configuration properties of the app according to the profile environment which currently up and active.
3) use a Spring Cloud API Gateway which can log and trace every incoming request that hits the server and forwards it to the relevant service attached to it. This will also require setting up Spring Eureka Server or any Service Discovery Server like Consul, Zookeeper, etcd etc. This will help the services use Spring Cloud Load Balancer and enable load balancing the requests.

Any further suggestions would be highly appreciated.
