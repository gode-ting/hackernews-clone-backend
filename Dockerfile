FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD  ./target/hackernews-clone-backend-0.0.1-SNAPSHOT app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar