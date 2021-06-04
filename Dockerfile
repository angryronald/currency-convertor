FROM openjdk:11

RUN apt-get -y update && apt-get install -y maven

WORKDIR /currency
COPY . .
RUN mvn clean compile assembly:single

RUN ["mkdir", "exec"]
COPY /target/currencyconverter-1.0-SNAPSHOT-jar-with-dependencies.jar /exec

FROM tomcat:jdk11-corretto

CMD ["java -jar exec/currency-1.0-SNAPSHOT-jar-with-dependencies.jar"]
