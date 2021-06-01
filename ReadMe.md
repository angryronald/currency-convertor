# CurrencyConverter

## Description
CurrencyConverter service for nosto.com technical test

### Code Style
1. SOLID Principle
2. Dependency Injection
3. Domain Driven Design
4. Command Query Responsibility Segregation

## Dependency Used
1. Maven
2. Rapidoid (Lightweight HTTP Server Library)
3. JUnit
4. Mockito

## Test
0. All test implement mockito and JUnit.
1. Unit test: [com.nosto.lib.csrf].CSRFTest.java
2. Integration test: 

## How to run 

### To run the service:
1. Since this project is develop using netbeans, It can be simply by click run button
2. Or also can be done by doing this comand: export JAVA_HOME=/currency/src/main/java and javac Currency.java (Since the java files is not compiled)

### Exposed port:
Listen to port 8000 by default

## Configuration:
Change configuration can be set by set up environment variables or update config/config.java file.

## Feature
1. Convert Currency by calling http://api.exchangeratesapi.io
2. Implementing CSRF (In order to be able to proceed the conversion process, need to call another API to get CSRF token)
3. Implementing Server-Timing (which produces the Latency Times / TTFB in metadata)
4. CSRF only valid for 5 minutes, later than that the token state as invalid and remove from in-mem.

## Endpoint
1. [GET] localhost:8000/v1/csrf: to collect csrf token
2. [POST] http://localhost:8000/v1/convert?from=EUR&to=IDR&amount=6500&csrf={csrfToken}: to proceed conversion