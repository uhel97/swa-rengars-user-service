FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
ARG USERS_PORT
COPY ${JAR_FILE} app.jar
EXPOSE ${USERS_PORT}
ENTRYPOINT ["java","-jar","/app.jar"]