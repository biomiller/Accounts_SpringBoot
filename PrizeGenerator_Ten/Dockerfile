FROM maven:latest AS maven

COPY . .

RUN mvn package

EXPOSE 8080

ENTRYPOINT java -jar /target/PrizeGenerator-0.0.1-SNAPSHOT.jar
