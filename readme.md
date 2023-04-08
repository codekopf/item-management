# Item Management

Item management is a simple project to demonstrate my ability to develop microservices. It is a Spring Boot application with a Postgres database which allows you to manage Items.

The whole project was inspired by my architect who give a task to create such microservice to every new candidate. I decided to create a public repository with my solution and track time to figure out how much time it takes to create working prototype.

This project aims for Java 20.

## How to run project locally?

Create a PostgreSQL container with: 

```
$ docker run --name my-postgres-container \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=mydatabase \
-p 5432:5432 \
-d postgres
```

This should create PostgreSQL container with new database "mydatabase". You can change database name, user and password in application.properties file.

Create an H2 container with:

```
$ docker run --name=MyH2Instance \
-v /path/to/local/data_dir:/opt/h2-data \
-e H2_OPTIONS=-ifNotExists \
-p 1521:1521 \
-p 81:81 \
-d oscarfonts/h2
```

This should create H2 database for testing purposes.

## API documentation

You can find a Swagger UI at http://localhost:8080/swagger-ui/index.html

## Issue debugger
If you have problems with Liquibase, use "logging.level.liquibase=DEBUG" in application.properties file.
