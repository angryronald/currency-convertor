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
0. All test implement mockito and/or JUnit.
1. Unit test: [com.nosto.lib.csrf].CSRFTest.java
2. Integration test: [com.nosto.internal.currency.infrastructure.external].ConverterExternalTest.java

## How to run 

### To run the service:
1. Since this project is develop using netbeans, It can be simply by click run button
2. Or also can be done by doing this comand: export JAVA_HOME=/currency/src/main/java/com/nosto/main and javac Currency.java (Since the java files is not compiled)

### Exposed port:
Listen to port 8081 by default

## Configuration:
Change configuration can be set by set up environment variables or update config/config.java file.

## Feature
1. Convert Currency by calling http://api.exchangeratesapi.io
2. Implementing CSRF (In order to be able to proceed the conversion process, need to call another API to get CSRF token)
3. Implementing Server-Timing (which produces the Latency Times / TTFB in metadata)
4. CSRF only valid for 5 minutes, later than that the token state as invalid and remove from in-mem.

## Endpoint
1. [GET] localhost:8081/v1/csrf: to collect csrf token
2. [POST] http://localhost:8081/v1/convert?from=EUR&to=IDR&amount=6500&csrf={csrfToken}: to proceed conversion

## Package Structure
1. [Default Package - src/main/java]: stores Main Class (Currency.java)
2. [com.nosto.config]: Stores Config class which manage the whole configuration data logic on which stored in environment variables but also have default value.
3. [com.nosto.http]: Stores Route class which compile all routes from all Endpoint spreads out across domain
4. [com.nosto.container]: Stores Container class which represents Dependency Injection Container which stores all needed dependency.
5. [com.nosto.internal.global]: Stores Global class which may stores global variable that can be used across domain.
6. [com.nosto.internal.currency]: Represents the Domain Boundary (Business Domain Representation) for currency inside this package will be separated into Infrastructure Layer, Domain Layer, Application Layer, and Endpoint Layer.
7. [com.nosto.internal.currency.infrastructure]: Represents the infrastructure layer which manage the responsibility of repository, process to call another domain, process to call another external services, etc.
8. [com.nosto.internal.currency.infrastructure.external]: Stores the Object class (ExternalCurrency) and Interface (ConverterExternal) which represents the abstraction of the process on calling other services.
9. [com.nosto.internal.currency.infrastructure.external.http]: Stores ConverterHttp Class which is represents the implementation of ConverterExternal interface.
10. [com.nosto.internal.currency.domain]: Stores all domain object classes.
11. [com.nosto.internal.currency.domain.service]: Represents the package which stores all domain services package.
12. [com.nosto.internal.currency.domain.service.currency]: Stores CurrencyService class and CurrencyServiceInterface interface. The class represents the implementation of the interface which focusing on processing object and calling infrastructure dependency and also on aggregation.
13. [com.nosto.internal.currency.application]: Stores Application class which encapsulate all Queries and Commands classes.
14. [com.nosto.internal.currency.application.query]: Stores queries classes which having business logic and only focusing only in reading data
15. [com.nosto.internal.currency.application.command]: Stores commands classes which having business logic and focusing on update, insert and delete process.
16. [com.nosto.lib]: Stores all internal classes (internal library) which doesn't relate with business logic and can be use across domain

## Notes
1. The test will focusing on testing the interface
2. I am wholly cover the test case on the test
3. I am not fully provided the test to reach the covered rate
4. I am not implementing any API Caching at this rate since the currency may changes very fast.

## Heroku
1. The service also can be accessed in https://polar-ocean-02943.herokuapp.com/v1/csrf

### Deploying to heroku 
1. heroku deploy:jar currency/target/currency-1.0-SNAPSHOT-jar-with-dependencies.jar
2. using procfile and git push heroku master