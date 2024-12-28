FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/PaymentService-0.0.1-SNAPSHOT.jar /app

EXPOSE 8082

CMD ["java", "-jar", "PaymentService-0.0.1-SNAPSHOT.jar"]