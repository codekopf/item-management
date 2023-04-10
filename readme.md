# Item Management

Item management is a simple project to demonstrate my ability to develop microservices. It is a Spring Boot application with a Postgres database which allows you to manage Items.

The whole project was inspired by my architect who give a task to create such microservice to every new candidate. I decided to create a public repository with my solution and track time to figure out how much time it takes to create working prototype.

This project aims for best practices and state-of-the-art technology.

## Original assignment

An online shopping owner asks for renewing his online shopping system, because the current one cannot handle in parallel many customers.

The aim is to use the current fort-end but split the monolith system into multiple microservices. One of there microservices will provide Item management and search of Items. You can take a list of Items from the web site: https://www.shopify.com/blog/trending-products . At the web-site there are 17 categories, you can add whatever color to each item.

### Technical requirements

+ Create microservice with use of spring-boot, JPA.
+ Use H2 database for development and PostgreSQL for production.
+ Use deployment of database with Liquibase.
+ Create docker file to be able to create image and container in your local environment.
+ Create helm chart to deploy the docker image into the locally installed Kubernetes e.g. minikube.
+ Use maven for compiling and building your code.
+ Write readme file so that the new developer is able to understand the goal of microservice.
+ Include the Postman project.

### Tasks

+ Create domain model of Items.
+ Create database model of Items.

![original assignment model](https://github.com/codekopf/item-management/documentation/img/original_assignment_model.png)

## REST API

+ Add or create Items, Color, Categories.
+ Add API, where the system is reading all items and then checking if similar item already exit in DB. Use java stream API.
+ Create validation of color with help of lambda expression. Compare color e.g. red with rgb value. If color is red return true otherwise false.
+ Search Item by name.
+ Search Items by category.
+ Search Item by price.
+ Search Item by color.
+ Read all items from DB, and filter them one color that will be provided by user as query parameter. Use java stream API.
+ Create postman project to create: items, colors, categories and the above API calls.

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

You can connect to H2 console at http://localhost:81/

## API documentation

You can find a Swagger UI at http://localhost:8080/swagger-ui/index.html

## Issue debugger
If you have problems with Liquibase, use "logging.level.liquibase=DEBUG" in application.properties file.
