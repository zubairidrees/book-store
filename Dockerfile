FROM openjdk:11.0-jre-slim
EXPOSE 8080
ARG JAR_FILE=target/bookstore-1.0.0.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]