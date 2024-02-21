FROM openjdk:17-ea-slim
VOLUME /tmp
COPY target/user_micro_service-1.0.jar UserService.jar
ENTRYPOINT ["java", "-jar", "UserService.jar"]