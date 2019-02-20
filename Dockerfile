FROM maven:3.6-jdk-8-alpine
RUN mkdir /app
VOLUME /app
COPY pom.xml /app-tmp/
COPY data-access/pom.xml /app-tmp/data-access/
COPY deployment-jaxws/pom.xml /app-tmp/deployment-jaxws/
COPY exterminatus-service/pom.xml /app-tmp/exterminatus-service/
COPY jaxws-client/pom.xml /app-tmp/jaxws-client/
COPY standalone-jaxws/pom.xml /app-tmp/standalone-jaxws/
WORKDIR /app-tmp
RUN mvn -fn dependency:go-offline
#force downloading of plugins
RUN mvn -fn install
COPY ./data-access/ /app-tmp/data-access/
COPY ./deployment-jaxws/ /app-tmp/deployment-jaxws/
COPY ./exterminatus-service/ /app-tmp/exterminatus-service/
COPY ./jaxws-client/ /app-tmp/jaxws-client/
COPY ./standalone-jaxws/ /app-tmp/standalone-jaxws/

COPY ./docker-files/config.properties /app-tmp/standalone-jaxws/src/main/resources/
COPY ./docker-files/datasource.properties /app-tmp/standalone-jaxws/src/main/resources/

RUN mvn clean install
ENTRYPOINT cp -r /app-tmp/* /app/
