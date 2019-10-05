FROM openjdk:8
VOLUME /tmp
ADD target/email-service-worker-0.0.1-SNAPSHOT.jar email-service-worker-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","email-service-worker-0.0.1-SNAPSHOT.jar"]