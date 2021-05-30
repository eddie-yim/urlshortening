# build
FROM adoptopenjdk:11-jdk-hotspot AS builder
COPY gradle gradle
COPY build.gradle .
COPY gradlew .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM adoptopenjdk:11-jdk-hotspot
COPY --from=builder build/libs/*-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]