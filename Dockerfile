FROM adoptopenjdk/openjdk11:alpine-jre

ADD build/libs/phasmophobiaapi-0.1.jar phasmophobiaapi.jar

EXPOSE 8080

HEALTHCHECK --interval=15s --timeout=3s --retries=3 \
 CMD wget --spider 127.0.0.1:8080/health

#CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar hue-bridge-api.jar
ENTRYPOINT ["phasmophobiaapi.jar"]
