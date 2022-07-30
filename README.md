# Bookstore
A Spring-Boot based REST API with the following functions:  
•&nbsp;CRUD operations on Book entity  
•&nbsp;Checkout operation for single or multiple books returning the total payable amount after calculating discount.  

# Tools and Libraries
### Below listed tools and libraries are used to develop book store APIs:

•&nbsp;Intellij IDEA

•&nbsp;Javax Validations for api validations

•&nbsp;Swagger for api docs

•&nbsp;In Memory h2- Database

•&nbsp;MapStruct for dto entity mapping

•&nbsp;Java 11

•&nbsp;Spring boot 2.5.5

•&nbsp;Junit & Mockito for unit testing

•&nbsp;Control advice for Global exception handling

•&nbsp;Postman for api testing

# Sequence Diagram
## Add Book
![](D:\Zubair\Workspace\bookstore\SequenceDiagram\BookController_createBook.svg)

## Get Book
![](D:\Zubair\Workspace\bookstore\SequenceDiagram\BookController_getBookById.svg)

## Update Book
![](D:\Zubair\Workspace\bookstore\SequenceDiagram\BookController_updateBook.svg)

## Delete Book
![](D:\Zubair\Workspace\bookstore\SequenceDiagram\BookController_deleteBookById.svg)

## Checkout
![](D:\Zubair\Workspace\bookstore\SequenceDiagram\CheckoutController_checkout.svg)

# Execute Unit Tests
:\bookstore> mvn clean test

# Package Application
:\bookstore> mvn clean package -DskipTests

# Run Application
:\bookstore> java -jar target/bookstore-0.0.1.jar

# Build Docker Image
:\bookstore> docker build -t bookstore .  

# Run BookStore Docker Container
:\bookstore> docker run -d --name bookstore -p 8080:8080 -t bookstore:latest  

# Access Swagger-UI
http://localhost:8080/bookstore/swagger-ui.html

# Access API-Docs
http://localhost:8080/bookstore/api-docs

# Access Performance Metrics
http://localhost:8080/bookstore/actuator


