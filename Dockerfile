FROM openjdk:11 as builder

RUN apt-get -y update && apt-get install -y maven

WORKDIR currencyconverter
COPY . .
RUN mvn clean compile assembly:single

RUN ["mkdir", "exec"]
COPY currency/target/currency-1.0-SNAPSHOT-jar-with-dependencies.jar exec/
RUN ls -a exec/

FROM tomcat:jdk11-corretto
RUN ["mkdir", "cmd"]
WORKDIR cmd
COPY --from=builder currencyconverter/exec/currency-1.0-SNAPSHOT-jar-with-dependencies.jar .
RUN ls -a
ENTRYPOINT java -jar currency-1.0-SNAPSHOT-jar-with-dependencies.jar
