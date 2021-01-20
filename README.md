### Setup
After cloning the repo then you will need to cd into the git-localconfig-repo and then do:
```
git init
git add . 
git commit -m "."
```
Otherwise it will not recognize the file from the git repo. 

Also - run a duplicate instance of the limits service on a different port in IntelliJ by selecting Run->Edit Configurations and duplilcate the instance.  Then in VM options enter: -Dserver.port=8081

When updating the config properties from the git repository you will need to do a git add and commit again AND then execute these endpoints:
```
POST http://localhost:8080/actuator/refresh
POST http://localhost:8081/actuator/refresh
```
If you don't refresh then you will not be able to see the updated config properties on the respective port of the limits service!  Calling this refresh on each instance would not be ideal (especially if we had hundreds of instances).
Spring Cloud Bus provides a solution for this!  Add the below dependency to both the limits and spring-cloud-config-server service pom.xml:

```
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-bus-amqp</artifactId>
		</dependency>
```

Also - use Docker to run an image of RabbitMq.  At startup all instances register with the bus which connect to RabbitMq.
```
docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```

Then you only need to execute the below url once.  After you can view the updated config properties on all instances of limits.
```
http://localhost:8080/actuator/bus-refresh
```


You can set the profile as **default** or **dev** in the limits-service

### Execution
You can view the configuration from the git repo at 
```
http://localhost:8888/limits-service/{default/dev} 
http://localhost:8080/limits
```

### Design
* Architect Diagram
