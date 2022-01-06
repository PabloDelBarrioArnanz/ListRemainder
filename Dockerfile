FROM openjdk:17-jdk-alpine
ARG SPRING_DATASOURCE_URL
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]