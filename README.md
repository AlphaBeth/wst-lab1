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
