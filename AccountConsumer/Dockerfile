FROM maven:latest AS maven

COPY . .

RUN mvn package

EXPOSE 8084

ENTRYPOINT java -jar /target/AccountConsumer-0.0.1-SNAPSHOT.jar
