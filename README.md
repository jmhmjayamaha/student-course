## Project Introduction

`The project includes two main entities Student and Course, along with a supporting entity Address. CRUD operations have been implemented for both the Student and Course entities, ensuring comprehensive data management. An H2 in-memory database has been used for data persistence, making it suitable for quick testing and development. A Postman collection containing all the API requests can be found in the parent folder location, providing a convenient way to interact with the API endpoints. Additionally, Aspect-Oriented Programming (AOP) has been integrated for logging purposes, and the logging configuration is managed under the config package, ensuring clear and structured application monitoring.`

## Used Technologies
1. Java 17
2. Spring boot
3. AOP
4. H2 in memory database
5. Mockito
6. postman

### Docker command

``docker build -t <docker-image-name> .``

``docker run -p 8080:8080 <docker-image-name>``

## You can find the API information here

- http://localhost:8080/swagger-ui/index.html 
