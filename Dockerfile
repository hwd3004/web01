FROM openjdk:11

ARG JAR_FILE=./build/libs/web01-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENV DOCKERIZE_VERSION v0.2.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

ENTRYPOINT ./docker-entrypoint.sh