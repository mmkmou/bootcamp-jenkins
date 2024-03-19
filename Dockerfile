FROM openjdk:17-jdk-slim
COPY ./target/transactions-0.0.1-SNAPSHOT.jar /usr/app/transactions-0.0.1-SNAPSHOT.jar
COPY ../src/main/resources/application-docker.properties /usr/app/application.properties
COPY ./entrypoint.sh /usr/bin/entrypoint.sh
WORKDIR /usr/app
RUN chmod +x /usr/bin/entrypoint.sh
EXPOSE 8080
ENTRYPOINT ["entrypoint.sh"]