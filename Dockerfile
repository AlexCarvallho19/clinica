FROM adoptopenjdk/maven-openjdk8
MAINTAINER daniel@dcx.ufpb.br

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]