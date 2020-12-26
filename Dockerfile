FROM adoptopenjdk/openjdk11:alpine-jre

ADD build/distributions/phasmophobiaapi-0.1.tar /

EXPOSE 8080

HEALTHCHECK --interval=15s --timeout=3s --retries=3 \
 CMD wget --spider 127.0.0.1:8080/health

ENTRYPOINT ["phasmophobiaapi-0.1/bin/phasmophobiaapi"]
