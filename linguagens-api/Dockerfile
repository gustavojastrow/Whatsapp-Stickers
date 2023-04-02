FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/linguagensapi-0.0.1-SNAPSHOT.jar linguagensapi.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar linguagensapi.jar --spring.config.location=file:/etc/secrets/application.properties
