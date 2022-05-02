FROM openjdk:8-jre-alpine

COPY target/taskmanager-0.0.1.jar /taskmanager.jar

EXPOSE 8080

RUN apk --no-cache add curl

CMD ["java", "-jar", "/taskmanager.jar"]