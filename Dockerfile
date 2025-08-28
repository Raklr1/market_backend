FROM openjdk:21-slim

COPY target/market-backend.jar /app/market-backend.jar

WORKDIR /app

EXPOSE 8081

CMD [ "java", "-jar", "market-backend.jar" ]