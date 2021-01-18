# Netflix and Spring Boot

* [Smartbear - Why you can't talk about microservices without mentioning Netflix](https://smartbear.com/blog/develop/why-you-cant-talk-about-microservices-without-ment/)

* [okta](https://www.baeldung.com/spring-security-okta)

* Chaos Monkey

![Netflix](https://lh3.googleusercontent.com/proxy/2xaXxOXhvZdjlrKCYzY9vCE1LX58JiRAnNiJyMb0WWZX1aSwFzgi3wRD8A7rcGOQwPofkz5o3aLiFYz2Rvk-583Je73woc-YTzzD7VXSl8Pl-o7Z)

![Netflix](https://i1.wp.com/samirbehara.com/wp-content/uploads/2019/05/netflix-oss-framework.png?fit=1215%2C701&ssl=1)


### Setup
* After cloning the repo then you will need to do a git init in the git-localconfig-repo. 
* You can set the profile as **default** or **dev** in the limits-service

### Execution
* You can view the configuration from the git repo at http://localhost:8888/limits-service/{default/dev} 

### Design
* Architect Diagram


#### Test
* http://localhost:8000/currency-exchange/from/USD/to/INR
```
{
    "id": 10001,
    "from": "USD",
    "to": "INR",
    "conversionMultiple": 65.0,
    "port": 8000
}
```

* http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1000

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
