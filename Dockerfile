FROM maven:3.8.6-eclipse-temurin-17-alpine

WORKDIR .
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run