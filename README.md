# Web service technologies, Laboratory work #1

## Task description

> В данной работе требуется создать таблицу в БД, содержащую не менее 5 полей, а
> также реализовать возможность поиска по любым комбинациям полей с помощью
> SOAP-сервиса. Данные для поиска должны передаваться в метод сервиса в качестве
> аргументов.
> 
> Веб-сервис необходимо реализовать в виде standalone-приложения и
> J2EE-приложения. При реализации в виде J2EE-приложения следует на стороне
> сервера приложений настроить источник данных, и осуществлять его инъекцию в коде
> сервиса.
> 
> Для демонстрации работы разработанных сервисов следует также разработать и
> клиентское консольное приложение.

## Requirements

- Java 8
- Maven 3+
- Glassfish 4.0
- Postgresql 9.3+

## Getting started

Start with typing 

`mvn clean install`

in project root directory.

## Project structure

The project consists of some modules:

- data-access -- all database-related code (entity classes, data access objects, utilities for query generation)
- exterminatus-service -- implementation of JAX-WS service
- deployment-jaxws -- packaging and configuration of exterminatus service for deployment onto application server
- standalone-jaxws -- standalone version of exterminatus service
- jaxws-client -- console client for web service

# Docker build

Docker build is available.

`docker-compose up`

## Services

- build-app - auxiliary service to build all source code and copy it to shared volume
- postgres - common database
- glassfish - application deployed on glassfish servers, see deployment-jaxws
- standalone-exterminatus - standalone version of application, see standalone-jaxws

### build-app

Copies all source code and build it.
On start copies all built code to `/build` catalogue (expected to be a mount point).

See `Dockerfile`.

### postgres

PostgreSQL container to store data.

Init script - `docker-files/init.sql`.

Dockerfile - `Dockerfile-data`.

### glassfish

Image with glassfish 4.0 and deployed application, see `Dockerfile-glassfish`.
Deploys all wars from path `/build/deployment-jaxws/target/` - mount volume with built code for correct behaviour.

By default create admin user with username 'admin' and password 'admin'. Specify build arg ADMIN_PASS to change password.

Default ports:

- 8080 - http, mapped to host at 9090
- 8181 - https, mapped to host at 9191
- 4848 - admin console, mapped to host at 9393

Useful urls:

- `http://localhost:9090/app/ExterminatusServiceService` - endpoint for exterminatus service
- `http://localhost:9090/app/ExterminatusServiceService?wsdl` - endpoint for exterminatus service
- `https://localhost:9393` - web admin console

Configuration:

- `docker-files/domain.xml` - `domain.xml` for glassfish. Contains description of JDBC connection pool
  and JDBC resource required by application
- `docker-files/glassfish-web.xml` - deployment descriptor for application to define context root of application
  (`/app` by default)
- `--build-arg ADMIN_PASS` - specify admin password ('admin' by default)
- `docker-files/start-glassfish.sh` - container startup script (copies war, start server and follow logs)

### standalone-exterminatus

Standalone version of exterminatus service inside docker container (see standalone-jaxws).

Default ports:
- 8080 - http, mapped to host at 9999

Useful urls:

- `http://localhost:9999/EXTERMINATE` - service endpoint
- `http://localhost:9999/EXTERMINATE?wsdl` - service WSDL

Configuration:

- `docker-files/config.properties` - config of service
- `docker-files/datasource.properties` - connection to database

Dockerfile - `docker-files/Dockerfile-standalone`.
