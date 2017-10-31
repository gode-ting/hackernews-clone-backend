FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD  /home/travis/build/gode-ting/hackernews-clone-backend/target/hackernews-clone-backend-0.0.1-SNAPSHOT app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar