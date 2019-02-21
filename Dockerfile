FROM maven:3.6-jdk-8-alpine
RUN mkdir /build
VOLUME /build
COPY pom.xml /build-tmp/
COPY data-access/pom.xml /build-tmp/data-access/
COPY deployment-jaxws/pom.xml /build-tmp/deployment-jaxws/
COPY exterminatus-service/pom.xml /build-tmp/exterminatus-service/
COPY jaxws-client/pom.xml /build-tmp/jaxws-client/
COPY standalone-jaxws/pom.xml /build-tmp/standalone-jaxws/
WORKDIR /build-tmp
RUN mvn -fn dependency:go-offline
#force downloading of plugins
RUN mvn -fn install
COPY ./data-access/ /build-tmp/data-access/
COPY ./deployment-jaxws/ /build-tmp/deployment-jaxws/
COPY ./exterminatus-service/ /build-tmp/exterminatus-service/
COPY ./jaxws-client/ /build-tmp/jaxws-client/
COPY ./standalone-jaxws/ /build-tmp/standalone-jaxws/

COPY ./docker-files/config.properties /build-tmp/standalone-jaxws/src/main/resources/
COPY ./docker-files/datasource.properties /build-tmp/standalone-jaxws/src/main/resources/
COPY ./docker-files/glassfish-web.xml /build-tmp/deployment-jaxws/src/main/webapp/WEB-INF/

RUN mvn clean install
ENTRYPOINT cp -r /build-tmp/* /build/
