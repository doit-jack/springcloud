FROM openjdk:21-ea-jdk-slim
VOLUME /tmp
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY build/libs/configservice-1.0.jar ConfigService.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${ACTIVE_PROFILE}","ConfigServce.jar"]