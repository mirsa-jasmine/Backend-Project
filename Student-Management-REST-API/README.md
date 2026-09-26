Student Management REST API

A Spring Boot REST API for managing student records. This project was built to practice backend development concepts including REST API development, DTOs, validation, exception handling, automated API testing, and frontend-backend integration.

Features

Add a student

Get all students

Get a student by ID

Update student details

Delete a student

Input validation

Global exception handling

Custom StudentNotFoundException

DTO-based request and response handling

Automated API testing with MockMvc

Simple web frontend

Search students from the frontend

Edit students through the frontend

Delete students through the frontend

In-memory data storage using ArrayList


Tech Stack

Java

Spring Boot

Spring Web MVC

Maven

Bean Validation

JUnit

MockMvc

HTML

CSS

JavaScript


API Endpoints

Method	Endpoint	Description

GET	/students	Get all students
GET	/students/{id}	Get a student by ID
POST	/students	Add a new student
PUT	/students/{id}	Update student details
DELETE	/students/{id}	Delete a student


Request Example

POST /students

{
  "name": "John",
  "department": "Computer Science",
  "age": 20
}

Response

{
  "id": 1,
  "name": "John",
  "department": "Computer Science",
  "age": 20
}

Validation

The API validates incoming student data.

Name cannot be blank

Department cannot be blank

Age must be at least 17


Invalid requests are rejected at the request boundary using Bean Validation.

Exception Handling

The project uses:

Custom StudentNotFoundException

@ControllerAdvice

Global exception handling


Requests for students that do not exist return a 404 Not Found response.

DTO Architecture

The API separates request and response data from the internal Student model.

StudentRequest
      ↓
StudentMapper
      ↓
Student
      ↓
StudentMapper
      ↓
StudentResponse

This keeps the API layer separated from the internal model.

Automated Testing

The API includes automated tests using MockMvc.

Tests cover:

GET requests

POST requests

PUT requests

DELETE requests

Invalid student IDs

Validation failures

HTTP response status codes

JSON response values


Frontend

A simple frontend is included and served directly by Spring Boot.

src/main/resources/static/
├── index.html
├── style.css
└── app.js

The frontend allows users to:

View students

Add students

Edit students

Delete students

Search students

Refresh the student list


Running the Project

Run the Spring Boot application using your IDE or Maven.

Then open:

http://localhost:8080/

The frontend is served directly by the Spring Boot application.

The REST API can also be accessed through:

http://localhost:8080/students

Persistence

Student data is currently stored in memory using an ArrayList.

This means the data is lost when the application stops.

Database persistence with MySQL, Spring Data JPA, and Hibernate is planned for a future project.

What I Practiced

This project helped me practice:

REST API development

Spring Boot

Dependency Injection

DTO architecture

DTO mapping

Bean Validation

Exception handling

HTTP status codes

Automated API testing

Frontend-backend communication

Git and GitHub