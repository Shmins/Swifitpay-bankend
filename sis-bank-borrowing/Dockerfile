FROM openjdk:17

WORKDIR /app

COPY target/borrowing-0.0.1-SNAPSHOT.jar /app/sis-bank-borrowing.jar

EXPOSE 8082

ENTRYPOINT [ "java", "-jar", "sis-bank-borrowing.jar" ]