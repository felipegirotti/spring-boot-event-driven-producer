# PLACES MICROSERVICE - Event Driven with SpringBoot and RabbitMQ or SNS Producer

This is a simple example of microservice, we are using the event driven architecture. 

The master branch uses RabbitMQ, the branch `sns-1` we change the broker to sns instead rabbitmq.

For more details of consumer see the example here: [https://github.com/felipegirotti/spring-boot-event-driven-consumer](https://github.com/felipegirotti/spring-boot-event-driven-consumer)

## API
We expose a simple CRUD HTTP API.   
- POST `/api/v1/place` Body:
  ```json
  {   
      "name": "DRZ Geotecnologia e Consultoria",
      "latitude": -23.3103803,
      "longitude": -51.1658525,
      "client_id": 1
  }
  ```  
- GET `/api/v1/place/{id}`

- PUT `/api/v1/place/{id}` Body:
    ```json
    {   
         "name": "DRZ Geotecnologia e Consultoria UP",
         "latitude": -23.3103803,
         "longitude": -51.1658525,
         "client_id": 1
    }
    ```
- DELETE `/api/v1/place/{id}`

From POST/PUT we emit an event with routing key `places.place.save`, for delete, we are using the same exchange and queue but the routing key is different `places.place.delete`

## Using SNS
To more details about how SNS works [click here](https://aws.amazon.com/sns/)   
First, change the branch for `sns-1`       
   
You need setup into AWS:
 - create a SNS topic
 - create a SQS to subscribe the SNS
 - create a user with the permission to send to SNS
 
 Use your credentials into the proper env vars and the topic name
 ```bash
 SENDER_TOPIC_EXCHANGE_NAME={{YOUR_TOPIC_NAME}}
 AWS_CREDENTIAL_KEY_ID={{YOUR_KEY_ID}}
 AWS_CREDENTIAL_SECRET={{YOUR_SECRET}}
 AWS_REGION_STATIC={{YOU_REGION}}
 ```
 The region should be capitalize e.g `US-WEST-2`
 

## Run locally
We are using env variables to setup the database properties and others configs  
See the .env.example and export your variables.  
e.g:    
```bash
export $(cat .env.example | xargs)
mvn spring-boot:run
```

### Docker compose
There a few dependencies, Mysql and RabbitMQ, those are configured into the docker-compose.yaml.    
Run `docker-compose up -d`

### Build a image with docker
For build a image you should first create a jar file with this command below:   
```bash
mvn clean package
```

After that just build the image (pay attention on the file of jar):    
```bash
docker build . -t {{REPLACE_YOUR_DOCKER_HUB_NAME}}/place-java-example --build-arg JAR_FILE=target/place-0.0.1-SNAPSHOT.jar
```

And push the image to [DockerHub](https://hub.docker.com/)  
```bash
docker push {{REPLACE_YOUR_DOCKER_HUB_NAME}}/place-java-example
```
