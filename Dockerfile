FROM maven:3.6.3-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build app/target/dockerDemo-0.0.1-SNAPSHOT-jar-with-dependencies.jar ./demo.jar

EXPOSE 8080

CMD ["java", "-jar", "demo.jar"]
