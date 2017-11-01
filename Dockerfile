FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD  ./target/hackernews-clone-backend-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Dspring.profiles.active=production -Djava.security.egd=file:/dev/./urandom -jar /app.jar