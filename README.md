### Execution
* You can view the configuration from the git repo at http://localhost:8888/limits-service/{default/dev} 

### Design
* Architect Diagram


#### Test
* GET http://localhost:8000/currency-exchange/from/USD/to/INR
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
