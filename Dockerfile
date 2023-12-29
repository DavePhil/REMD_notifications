FROM openjdk:17-alpine
WORKDIR /app
COPY target/remd_notifications.jar /app
CMD ["java","-jar","remd_notifications.jar"]