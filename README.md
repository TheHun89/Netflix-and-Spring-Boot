### Setup
* After cloning the repo then you will need to cd into the git-localconfig-repo and then do:
```
git init
git add . 
git commit -m "."
```
* Otherwise it will not recognize the file from the git repo. 

* When updating the config properties from the git repository you will need to do a git add and commit again AND then execute this endpoint:
```
POST http://localhost:8080/actuator/refresh
```
Spring Cloud Bus provides a solution for this!


* You can set the profile as **default** or **dev** in the limits-service

### Execution
* You can view the configuration from the git repo at 
```
http://localhost:8888/limits-service/{default/dev} 
http://localhost:8080/limits
```

### Design
* Architect Diagram
