# Simple wsdl2rest web service

This project is designed to create a simple web service for the user story presented by <https://github.com/bfitzpat/wsdl2rest-user-story>. Using a JAX-WS SOAP-based service, this project offers two methods:

* one method to get a random city from a JSON list compatible with the <https://openweathermap.org/forecast16> API - the thin.city.list.json file presents a much smaller file than city.list.json
* one method to take a city name as input and return a country code from the JSON 

These methods are then wrapped by Camel Rest DSL in <https://github.com/bfitzpat/wsdl2rest-user-story> and integrated into a larger integration.

## To run the SOAP-based JAX-WS RPC service

```bash
     mvn compile exec:java -Dexec.mainClass="com.simple.ws.SimpleWSPublisher"
```

You can also use the script:

```bash
    ./runwebservice.sh
```

The WSDL is then accesible at: http://localhost:9999/ws/random?wsdl
