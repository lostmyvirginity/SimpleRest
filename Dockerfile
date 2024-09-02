FROM openjdk:21-jdk
LABEL authors="lomyvi"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
