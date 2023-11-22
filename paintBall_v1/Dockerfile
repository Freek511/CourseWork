#FROM maven:3.8.4-openjdk-17 as builder
#WORKDIR /app
#COPY . /app/.
#RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM bellsoft/liberica-openjdk-alpine:18.0.2.1-1
WORKDIR /app
COPY target/paintBall_v1-0.0.1-SNAPSHOT.jar /app/paintBall_v1-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "paintBall_v1-0.0.1-SNAPSHOT.jar"]