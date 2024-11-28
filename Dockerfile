FROM openjdk:17-jdk-alpine
RUN apk --no-cache add msttcorefonts-installer fontconfig && \
    update-ms-fonts && \
    fc-cache -f
WORKDIR /app
EXPOSE 8080
COPY target/*.jar /app/sofka-test-backend.jar
CMD [ "java", "-jar", "/app/sofka-test-backend.jar" ]