FROM docker.totvs.io/tjf/maven:3.6.0

ADD . /maven-repo
WORKDIR /maven-repo

RUN mvn clean install

EXPOSE 8080

ARG JAR_FILE
RUN cp target/tjf-checkin-survey-0.2.0-RELEASE.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]