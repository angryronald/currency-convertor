FROM openjdk:11 as builder

RUN apt-get -y update && apt-get install -y maven

WORKDIR /currency
COPY . .
RUN mvn clean compile assembly:single

RUN ["mkdir", "exec"]
COPY /target/currencyconverter-1.0-SNAPSHOT-jar-with-dependencies.jar /exec/
RUN ls -a /exec/

FROM tomcat:jdk11-corretto
RUN ["mkdir", "cmd"]
COPY --from=builder /exec/currencyconverter-1.0-SNAPSHOT-jar-with-dependencies.jar /cmd/
RUN ls -a /cmd/
ENTRYPOINT ["java -jar cmd/currency-1.0-SNAPSHOT-jar-with-dependencies.jar"]
