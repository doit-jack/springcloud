FROM openjdk:21-ea-jdk-slim
VOLUME /tmp
COPY build/libs/userservice-1.0.jar UserService.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${ACTIVE_PROFILE}","UserService.jar"]