# Simple wsdl2rest user story

This project is designed to create a simple setup for the following scenario:

1. I have a simple Camel-based service I want to integrate with a SOAP-based service.
2. My simple Camel-based service calls out to a weather service with a city/country pair to get a forecast.
3. I want to integrate a SOAP service that serves up a random city/country pair as a Restful process to feed the Camel service

## To run the SOAP-based JAX-WS RPC service

```bash
     mvn compile exec:java -Dexec.mainClass="com.simple.ws.SimpleWSPublisher"
```

The WSDL is then accesible at: http://localhost:9999/ws/random?wsdl
