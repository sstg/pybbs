FROM java:8-alpine

ADD . /app/pybbs

WORKDIR  /app/pybbs

RUN ./mvnw -Dmaven.skip.test=true package && \
  mv target/pybbs-2.5.jar /app/ && \
  rm -fr /app/pybbs

CMD ["java", "-jar", "/app/pybbs-2.5.jar"]
