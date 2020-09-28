FROM openjdk:11-jre
WORKDIR usr/lib
ENV MONGO_DATABASE=userAuthDb
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
COPY ./target/UserAuthenticationService-0.0.1-SNAPSHOT.jar /usr/lib/UserAuthenticationService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "UserAuthenticationService-0.0.1-SNAPSHOT.jar"]
