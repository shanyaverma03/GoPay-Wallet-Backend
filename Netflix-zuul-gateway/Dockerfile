FROM openjdk:11-jre
WORKDIR usr/lib
COPY ./target/netflix-zuul-gateway-0.0.1-SNAPSHOT.jar /usr/lib/netflix-zuul-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "netflix-zuul-gateway-0.0.1-SNAPSHOT.jar"]
