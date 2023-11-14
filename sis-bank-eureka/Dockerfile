FROM openjdk:17

WORKDIR /app

COPY target/eureka-0.0.1-SNAPSHOT.jar /app/sis-bank-eureka.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "sis-bank-eureka.jar" ]