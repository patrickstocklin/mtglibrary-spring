FROM ubuntu:14.04
RUN \
  apt-get update && \
  apt-get -y upgrade && \
  apt-get install -y curl && \
  apt-get install -y  software-properties-common && \
  add-apt-repository ppa:webupd8team/java -y && \
  apt-get update && \
  echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
  apt-get install -y oracle-java8-installer && \
  apt-get clean

# Set environment variables.
RUN mkdir /root/app
ENV HOME /root/app
WORKDIR /root/app

EXPOSE 8080

COPY ./target/mtglibrary-spring-1.0-SNAPSHOT.jar app.jar

# Define default command.
CMD ["java", "-jar", "app.jar"]
