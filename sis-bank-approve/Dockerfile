FROM openjdk:17

WORKDIR /app

COPY target/approve-0.0.1-SNAPSHOT.jar /app/sis-bank-approve.jar

EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "sis-bank-approve.jar" ]