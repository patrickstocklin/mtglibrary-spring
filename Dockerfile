FROM openjdk:8-jre-alpine

# Set environment variables.
RUN mkdir /root/app
ENV HOME /root/app
WORKDIR /root/app

EXPOSE 8080

COPY ./target/mtglibrary-spring-1.0-SNAPSHOT.jar app.jar

# Define default command.
CMD ["java", "-jar", "app.jar"]
