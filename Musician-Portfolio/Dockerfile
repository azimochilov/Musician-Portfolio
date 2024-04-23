FROM vegardit/graalvm-maven:latest-java17 as builder
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY src /app/src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17-oracle
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
