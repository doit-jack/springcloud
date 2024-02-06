FROM openjdk:21-ea-jdk-slim
VOLUME /tmp
COPY build/libs/gateway-1.0.jar GatewayService.jar
ENTRYPOINT ["java","-Dspring.profiles.active=${ACTIVE_PROFILE}","-jar","GatewayService.jar"]