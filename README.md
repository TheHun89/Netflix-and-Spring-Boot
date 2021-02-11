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


### Module Setup
* Resilience4J is a fault tolerance library that replaces Hystrix.  It allows for fallback responses, rate limiting, retry requests(number of retries, amount of time to retry) and Circuit Breaker pattern to reduce load.  
* With microservices you may have a chain of services calling each other.  If one is experiencing latency or some sort of error that brings it down then that will use up memory and can bring down the main service calling it.  

* In this example we will need to add the following dependencies to the currency-exchange service:  
1. actuator
2. spring-cloud-starter-aop 
3. resilience4j-spring-boot2

Then we will create a controller (CircuitBreakerController).  This would simple return a string.
```
@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
    public String sampleApi() {
        log.info("Sample api call received");
        // ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
                    String.class);
        // return forEntity.getBody();
        return "sample-api";
    }
```

* If we uncomment the ResponseEntity portion it will fail.
* We can use the Retry annotation above the sampleApi method to have the method retried if it fails.  The default is 3x.
```@Retry(name="default") 
```

* If we want to customize the number of retries to 5 then we can change the Retry annotation to:
```@Retry(name="sample-api") 
```
* then we would want to add the below line to the app.properties:
```resilience4j.retry.instances.sample-api.maxRetryAttempts=5
```

* You can also use a fallback response if all retries fail.  You do this by adding the below method:
```public String hardcodedResponse(Exception ex) {
    return "fallback-response";
}
```
* Also change the Retry annotation to:
```@Retry(name="sample-api", fallbackMethod="hardcodedResponse") 
```

* You can configure the interval between wait times by adding below line to app.properties:
```resilience4j.retry.instances.sample-api.waitDuration=1s
```

* You can configure exponential backoff by adding the below line to app.properties.  This is bc each subsequent request will wait exponentially longer.
```resilience4j.retry.instances.sample-api.enableExponentialBackoff=true
```

* To use CircuitBreaker we will want to change the Retry annotation to:
```@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
```

* Now to see the magic you will need to fire off a lot of requests.  On Windows you can do 
``` watch -n 0.1 curl http://localhost:8000/sample-api
```
* -n 0.1 means its sending a request every 1/10 of a second.  You will see that the requests start firing off and after a while they will slow and stop in intervals.

* CircuitBreaker behaves by returning response w/o calling the method.  It directly returns a response when it knows the service being called is down.  There are three states - CLOSED, OPEN and HALF OPEN.  Closed is when you are calling the dependent microservice continuously.  Open is when circuit breaker directly returns fallback response.  Half open sends a percentage of requests to dependent microservice.  
* You can configure wait duration before circuit breaker attempts to call method of dependent method again.  When a certain percentage is successful then it returns to Closed state.  

* You can configure the failure rate threshhold % with the below line in the app.properties:
```resilience4j.circuitbreaker.instances.default.failureRateThreshold=90
```

* You can also configure rate limiting with the RateLimiter annotation (comment out the CircuitBreaker annotation)
```@RateLimiter(name="default")
```

* Then you can configure the requests per time (ie: 2 requests every 10 sec):
```resilience4j.ratelimiter.instances.default.limitForPeriod=2
resilience4j.ratelimiter.instances.default.limitRefreshPeriod=10s
```
* then run 
```watch -n 1 curl http://localhost:8000/sample-api
```

* You can also use the Bulkhead annotation to set the max concurrent calls

```resilience4j.bulkhead.instances.default.maxConcurrentCalls=10
resilience4j.bulkhead.instances.sample-api.maxConcurrentCalls=10
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
