## Task Manager

Spring boot application that demonstrates the usage of RESTful API using Spring boot, Liquibase and PostgreSQL.

## Tools and Technologies used

* Java 17
* Spring boot 3.2.0
* Hibernate 6.4.0
* JWT Authentication
* PostgreSQL
* Liquibase 4.24.0
* Maven
* JUnit 4.13.2
* Swagger


## Run the App

The Application is running via docker-compose:

```docker-compose up```



The server will start on port 8081.


## Explore Rest APIs

The app defines following APIs
(or you can use swagger-ui at the link: http://localhost:8080/swagger-ui/index.html)

All requests requires a basic authorization:

```username: tester```

```password: 123```

```
GET /taskmanager/task/getTasksByReporter?offset={offset}&limit={limit}&id={id}

Example URL: http://localhost:8080/taskmanager/task/getTasksByReporter?offset=0&limit=4&id=10
```
```
GET /taskmanager/task/getTasksByAssignee?offset={offset}&limit={limit}&id={id}

Example URL: http://localhost:8080/taskmanager/task/getTasksByAssignee?offset=0&limit=4&id=10
```
```
POST /taskmanager/task/add

Example URL: http://localhost:8080/taskmanager/task/add
Example Request body:

{
    "title": "New task",
    "description": "New task test",
    "status": "BACKLOG",
    "priority": "LOW",
    "assignee": "11"
}
```   
```
POST /taskmanager/task/update

Example URL: http://localhost:8080/taskmanager/task/update
Example Request body:

{
    "id":"18",
    "title": "Incorrect response updates",
    "description": "Got an incorrect response when making payment",
    "status": "BACKLOG",
    "priority": "MIDDLE",
    "assignee": "11",
    "reporter":"10"
}
```
```
GET /taskmanager/task/updateStatus?id={id}&status={status}

Example URL: http://localhost:8080/taskmanager/task/updateStatus?id=13&status=IN_PROGRESS
```
```
DELETE /taskmanager/task/delete?id={id}

Example URL: http://localhost:8080/taskmanager/task/delete?id=22
```
```
POST /taskmanager/comment/add

Example URL: http://localhost:8080/taskmanager/comment/add
Example Request body:

{
    "content":"test comment",
    "task":"11"
}
```
```
GET /taskmanager/comment/getComments?id={id}

Example URL: http://localhost:8080/taskmanager/comment/getComments?id=16
```


You can test them using postman or any other rest client.
