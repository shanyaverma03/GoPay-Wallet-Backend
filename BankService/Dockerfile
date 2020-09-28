FROM openjdk:11-jre
WORKDIR usr/lib
ENV MONGO_DATABASE=BankDb
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
COPY ./target/BankService-0.0.1-SNAPSHOT.jar /usr/lib/BankService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "BankService-0.0.1-SNAPSHOT.jar"]
