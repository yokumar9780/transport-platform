FROM eclipse-temurin:21 AS builder
WORKDIR /workspace
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} partner-service.jar
RUN java -Djarmode=layertools -jar partner-service.jar extract

FROM gcr.io/distroless/java21-debian12:latest
WORKDIR /workspace

COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
