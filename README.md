## Containerize Microservices with Docker Compose and MySQl Volumes

#### Setup
When you run the mvn package to build the image for currency-exchange be sure to have a container running the mysql image – otherwise it will error
```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 mysql:latest
```

There were some issues with using the volume and Spring Boot.  To demonstrate that you can persist and retain data after destroying a container follow the below steps:
1. First make sure all containers and volumes are deleted with:
```
docker container prune
docker volume prune
```
1. When you initially run the docker compose the SPRING.DATASOURCE.INITIALIZATION-MODE env var should be set to ALWAYS.  This will load the DATA.SQL inserts.  
2. Execute the below endpoint to add data to the mysql db:
```
POST http://localhost:8765/currency-exchange-service/currency-exchange 
{
    "id":10004,
    "from":"AUD",
    "to":"INR",
    "conversionMultiple":25.0,
    "port":0
}
```
3. Execute docker-compose down (this will destroy the containers)
4. Anytime you do docker-compose up after that set SPRING.DATASOURCE.INITIALIZATION-MODE=NEVER so there are no conflicts inserting duplicate data.  This is used to reuse volumes.
5. Execute the below endpoint and you should see the data you previously added (for id 10004)
```
GET http://localhost:8765/currency-exchange-service/currency-exchange
```

#### Test
```
http://localhost:8761/

RabbitMq – username/password:  guest/guest
http://localhost:15672/


GET http://localhost:8000/currency-exchange/from/USD/to/INR
GET http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR
```
```
{
    "id": 10001,
    "from": "USD",
    "to": "INR",
    "conversionMultiple": 65.0,
    "port": 8000
}
```
```
GET http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1000
GET http://localhost:8765/currency-conversion-service/currency-converter/from/EUR/to/INR/quantity/1000
```
```
{
  "id":10001,
  "from":"USD",
  "to":"INR",
  "conversionMultiple":65.0,
  "quantity":1000.0,
  "totalCalculatedAmount":65000.0,
  "port":8000
}
```
