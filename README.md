* NOTE: Eureka, Ribbon and Feign can have issues importing.  If this occurs do a maven clean package and thenNOTE: Feign and Ribbon can have issues importing. Be sure to have the exact Spring Boot and Spring Cloud version referenced in this pom.xml. If this occurs do a maven clean package and then reimport all maven projects.

### Configuration
* You can run multiple instances of the **currency exchange** service by using multiple ports. This would result in different ports being returned in the response body. To run a new instance on a different port in IntelliJ - select Run->Edit Configurations and duplilcate the instance. Then in VM options enter: -Dserver.port=8001 reimport all maven projects 

To launch an instance of a Zipkin server you can either download and run the jar 
```
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
```
or run a docker image (recommended)
```
docker run -d -p 9411:9411 openzipkin/zipkin
```

* Visit Zipkin UI at:
```
http://localhost:9411/zipkin/
```


#### Test
* http://localhost:8761/

* GET http://localhost:8000/currency-exchange/from/USD/to/INR
* GET http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR
```
{
    "id": 10001,
    "from": "USD",
    "to": "INR",
    "conversionMultiple": 65.0,
    "port": 8000
}
```

* GET http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1000
* GET http://localhost:8765/currency-conversion-service/currency-converter/from/EUR/to/INR/quantity/1000

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
