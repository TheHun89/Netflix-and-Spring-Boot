### Spring Cloud Load Balancer

* **DEPRECATION WARNING**: Spring Cloud Netflix Ribbon is now deprecated with Spring Boot 2.4+.  This branch has been refactored to use **Spring Cloud Load Balancer**.  Additions include adding the Eureka server service, setting Spring Boot version to 4.2 for all services and Spring Cloud version to 2020.0.0.  The Currency Exchange and Currency Conversion services pom.xmls had the following dependencies either added or renamed:  spring-cloud-starter-netflix-eureka-client and spring-cloud-starter-config.  Before the Ribbon dependency was needed for the load balancing.  Now there is a load balancing dependency included with the Eureka dependency.  So if you are using Feign and Eureka then load balancing is automatically applied for free.

* NOTE: Feign and Ribbon can have issues importing.  Be sure to have the exact Spring Boot and Spring Cloud version referenced in this pom.xml.  If this occurs do a maven clean package and then reimport all maven projects.

### Configuration

You can run multiple instances of the **currency exchange** service by using multiple ports. This would result in different ports being returned in the response body. To run a new instance on a different port in IntelliJ - select Run->Edit Configurations and duplilcate the instance. Then in VM options enter: -Dserver.port=8001

#### Test
```
* GET http://localhost:8000/currency-exchange/from/USD/to/INR
{
    "id": 10001,
    "from": "USD",
    "to": "INR",
    "conversionMultiple": 65.0,
    "port": 8000
}
```

* GET http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1000

```
{
  "id":10001,
  "from":"USD",
  "to":"INR",
  "conversionMultiple":65.0,
  "quantity":1000.0,
  "totalCalculatedAmount":65000.0,
  "port": <8000/8001>
}
```
