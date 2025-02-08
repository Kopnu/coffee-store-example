FROM eclipse-temurin:17-jre-alpine

ARG BACKEND_OPTS=""

COPY /shop-example-impl/build/libs/shop-example-impl-0.0.1-SNAPSHOT.jar /backend.jar


ENV MAX_RAM_PERCENTAGE=50
ENV JAVA_OPTS="--add-opens java.base/java.lang=ALL-UNNAMED"

CMD java -XX:MaxRAMPercentage=$MAX_RAM_PERCENTAGE $JAVA_OPTS -jar /backend.jar ${BACKEND_OPTS}
