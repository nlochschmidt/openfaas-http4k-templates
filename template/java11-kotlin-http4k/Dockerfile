FROM alpine:3.11 as minimal_jre

RUN apk --no-cache add openjdk11-jdk openjdk11-jmods

RUN /usr/lib/jvm/java-11-openjdk/bin/jlink \
    --verbose \
    --add-modules \
        java.base,java.sql,java.logging,java.xml,jdk.httpserver \
    --compress 2 --strip-debug --no-header-files --no-man-pages \
    --release-info="add:IMPLEMENTOR=nlochschmidt:IMPLEMENTOR_VERSION=minimal_jre" \
    --output "/opt/java_minimal"

FROM gradle:6.2.1-jdk11 as build
ENV APP_HOME=/app
WORKDIR $APP_HOME
WORKDIR /app
# This allows caching of dependencies for repeated builds
COPY ./build.gradle ./settings.gradle ./
COPY ./function/versions.gradle ./function/build.gradle ./function/settings.gradle ./function/
RUN gradle classes || return 0

COPY . .
RUN gradle shadowJar

FROM openfaas/of-watchdog:0.7.6 as watchdog

FROM alpine:3.11

ENV JAVA_HOME=/opt/jre
ENV PATH="$PATH:$JAVA_HOME/bin"

WORKDIR /app
COPY --from=minimal_jre /opt/java_minimal $JAVA_HOME
COPY --from=watchdog /fwatchdog /usr/bin/fwatchdog
COPY --from=build /app/build/libs/*-all.jar function.jar

RUN chown -R app /home/app
USER app

ENV function_process="java -jar /app/function.jar"
ENV mode="http"
ENV http_upstream_url="http://127.0.0.1:3000"

ENV write_timeout="15s"
ENV read_timeout="15s"

HEALTHCHECK --interval=3s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
