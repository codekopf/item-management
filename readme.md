# Item Management

Item management is a simple project to demonstrate my ability to develop microservices. It is a Spring Boot application with a Postgres database which allows you to manage Items.

The whole project was inspired by my architect who give a task to create such microservice to every new candidate. I decided to create a public repository with my solution and track time to figure out how much time it takes to create working prototype.

## How to run project locally?

Create a Postgres container with 

docker run --name my-postgres-container \
-e POSTGRES_USER=myuser \
-e POSTGRES_PASSWORD=mypassword \
-e POSTGRES_DB=mydatabase \
-p 5432:5432 \
-d postgres

## API documentation

You can find a Swagger UI at http://localhost:8080/swagger-ui/index.html
