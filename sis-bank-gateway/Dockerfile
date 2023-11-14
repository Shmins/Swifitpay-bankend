FROM openjdk:17

WORKDIR /app

COPY target/gateway-0.0.1-SNAPSHOT.jar /app/sis-bank-gateway.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "sis-bank-gateway.jar" ]