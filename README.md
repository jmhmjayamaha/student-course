## Project Introduction

`The project includes two main entities Student and Course, along with a supporting entity Address. CRUD operations have been implemented for both the Student and Course entities, ensuring comprehensive data management. An H2 in-memory database has been used for data persistence, making it suitable for quick testing and development. A Postman collection containing all the API requests can be found in the parent folder location, providing a convenient way to interact with the API endpoints. Additionally, Aspect-Oriented Programming (AOP) has been integrated for logging purposes, and the logging configuration is managed under the config package, ensuring clear and structured application monitoring.`

## Used Technologies
1. Java 17
2. Spring boot
3. AOP
4. H2 in memory database
5. Mockito
6. webclient
7. postman

### Security
``If the project needs to be enhanced in the future, you can add security using this approach. Start by adding the necessary Spring Security dependency to the pom.xml file. Next, create a security configuration class by extending WebSecurityConfigurerAdapter (for Spring Security versions before 5.7) or implementing SecurityConfigurer (for newer versions) to customize the security settings. Within this configuration, specify the authentication mechanism using inMemoryAuthentication for quick setup or integrate with a custom user details service to authenticate against a database. Define authorization rules to restrict access to specific endpoints based on user roles and set up a password encoder for secure password handling. For token-based security, integrate JWT (JSON Web Token) by implementing filters to handle token validation, ensuring secure request handling. Additionally, apply configurations such as CSRF protection, CORS settings, and custom login/logout endpoints. This approach will provide a robust and flexible security layer that can be seamlessly integrated as the project evolves.``

### Docker command

``docker build -t <docker-image-name> .``

``docker run -p 8080:8080 <docker-image-name>``

## You can find the API information here

- http://localhost:8080/swagger-ui/index.html 
