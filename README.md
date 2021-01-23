### Containerize Microservices with Docker Compose

#### Setup
Run the following command to launch the containers.  Note that we are launching multiple instances of currency-exchange using **scale**.
```
docker-compose up --scale currency-exchange=2 -d
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
