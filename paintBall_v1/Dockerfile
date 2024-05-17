##FROM maven:3.8.4-openjdk-17 as builder
##WORKDIR /app
##COPY . /app/.
##RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true
#
## Stage 1: Build stage
#FROM maven:3.8.4-openjdk-17-slim AS build
#
## Copy Maven files for dependency resolution
#COPY pom.xml ./
#COPY .mvn .mvn
#
## Copy application source code
#COPY src src
#
## Build the project and create the executable JAR
#RUN mvn clean install

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY paintBall_v1-0.0.1-SNAPSHOT.jar /app/paintBall_v1-0.0.1-SNAPSHOT.jar
EXPOSE 10000
CMD ["java", "-jar", "paintBall_v1-0.0.1-SNAPSHOT.jar"]