FROM maven:3.9.8-openjdk-21-slim as builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:21-jre-slim
WORKDIR /app
COPY --from=builder /usr/src/app/target/test-inditex-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8084
CMD ["java", "-jar", "app.jar"]

RUN groupadd -r app && useradd --no-log-init -r -g app app
USER app