FROM maven:latest AS maven

COPY . .

RUN mvn package

EXPOSE 8081

ENTRYPOINT java -jar /target/AccountNumGenerator-0.0.1-SNAPSHOT.jar
