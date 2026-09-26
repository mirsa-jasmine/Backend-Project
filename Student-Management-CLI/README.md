# Student Management System (CLI)

A command-line based Student Management System built in Java to practice Object-Oriented Programming (OOP), data management, file handling, and software architecture principles.

## Features

* Add a student
* Find a student by ID
* Update student details
* Remove a student
* Display total number of students
* Display the oldest student
* List all students
* Search students by name
* Sort students by age
* Save student records to a file
* Load student records from a file at startup

## Validation

The system performs validation for:

* Duplicate student IDs
* Negative IDs
* Negative ages
* Empty names
* Names containing only spaces

## Technologies Used

* Java
* HashMap
* ArrayList
* File I/O (BufferedReader / BufferedWriter)
* Interfaces
* Collections Framework

## Project Structure

```text
src/
└── com/studentmanagement
    ├── Main.java
    ├── model
    │   └── Student.java
    └── service
        ├── StudentOperations.java
        └── StudentService.java
```

## OOP Concepts Practiced

* Classes and Objects
* Constructors
* Getters and Setters
* Encapsulation
* Interfaces
* Abstraction
* Separation of Concerns

## Architecture

### Student (Model)

Represents student data.

### StudentOperations (Interface)

Defines the operations supported by the system.

### StudentService (Service Layer)

Contains the business logic and manages student records.

### Main (Presentation Layer)

Handles user interaction through the command-line interface.

## Data Persistence

Student records are stored in `students.txt`.

When the application starts:

* Existing records are loaded from the file.

When records are added, updated, or removed:

* Changes are saved automatically.

## Learning Outcomes

Through this project I learned:

* Designing Java applications using multiple layers
* Using interfaces to define behavior
* Managing collections with HashMap and ArrayList
* Reading and writing files
* Applying validation rules
* Using Git and GitHub for version control
* Structuring a project using OOP principles

## Future Improvements

* Database integration (MySQL/PostgreSQL)
* Logging
* Better exception handling
* Unit testing
* REST API version of the application

## Author

Mirsa Jasmine K P
