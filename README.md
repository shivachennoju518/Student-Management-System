🎓 Student Management System (SMS) – Backend

This repository contains the backend application of the Student Management System (SMS) developed using Spring Boot.
It provides secure RESTful APIs with JWT-based authentication for managing student data.

📌 Project Overview

The backend is responsible for:

User authentication & authorization

Role-based access control (Admin / User)

Managing student data (CRUD operations)

Handling security, validation, and exceptions

The backend communicates with a React frontend via REST APIs.

🛠️ Tech Stack

Java

Spring Boot

Spring Web

Spring Security

JWT (JSON Web Token)

Spring Data JPA

Hibernate

PostgreSQL

Maven

✨ Features

🔐 JWT-based authentication

👥 Role-based authorization

🧑‍🎓 Student CRUD operations

📦 DTO-based request & response handling

🔁 Entity–DTO mapping

⚠️ Global exception handling

🧱 Layered architecture

🔒 Secure API endpoints

📂 Backend File Structure
backend/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       ├── DemoApplication.java
│   │   │
│   │   │       ├── Config/
│   │   │       │   ├── AdminBootstrap.java
│   │   │       │   ├── AppBeansConfig.java
│   │   │       │   └── SecurityConfig.java
│   │   │
│   │   │       ├── Controller/
│   │   │       │   ├── AuthController.java
│   │   │       │   └── StudentController.java
│   │   │
│   │   │       ├── DTO/
│   │   │       │   ├── LoginRequest.java
│   │   │       │   ├── LoginResponse.java
│   │   │       │   ├── StudentCreateDTO.java
│   │   │       │   ├── StudentUpdateDTO.java
│   │   │       │   └── StudentResponseDTO.java
│   │   │
│   │   │       ├── Entity/
│   │   │       │   ├── Student.java
│   │   │       │   └── Role.java
│   │   │
│   │   │       ├── Exception/
│   │   │       │   ├── CustomException.java
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │
│   │   │       ├── jwt/
│   │   │       │   ├── AuthEntryPointJwt.java
│   │   │       │   ├── JwtAuthFilter.java
│   │   │       │   └── JwtUtils.java
│   │   │
│   │   │       ├── Mapper/
│   │   │       │   └── StudentMapper.java
│   │   │
│   │   │       ├── Repository/
│   │   │       │   └── StudentRepo.java
│   │   │
│   │   │       ├── Security/
│   │   │       │   └── StudentSecurity.java
│   │   │
│   │   │       ├── Service/
│   │   │       │   ├── StudentService.java
│   │   │       │   ├── StudentServiceImpl.java
│   │   │       │   └── CustomUserDetailsService.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│
├── pom.xml
└── README.md

🧠 Layer-wise Explanation (Interview Ready)
🔹 Controller Layer

Handles incoming HTTP requests and returns API responses.

AuthController → Login & authentication APIs

StudentController → Student CRUD APIs

🔹 Service Layer

Contains business logic.

StudentService → Interface

StudentServiceImpl → Implementation

CustomUserDetailsService → Spring Security user handling

🔹 Repository Layer

Handles database operations using JPA.

public interface StudentRepo extends JpaRepository<Student, Long>

🔹 Entity Layer

Represents database tables.

Student

Role

🔹 DTO Layer

Used to transfer data safely between client and server.

Prevents exposing entity directly

Improves API security

🔹 Mapper Layer

Maps Entity ↔ DTO.

Keeps code clean

Follows best practices

🔹 Security & JWT

Provides authentication and authorization.

JWT token generation & validation

Custom authentication filter

Secure endpoints

🔹 Exception Handling

Centralized exception management.

Custom exceptions

Global error handling

🔗 API Endpoints (Sample)
Method	Endpoint	Description
POST	/auth/login	User login
GET	/api/students	Get all students
POST	/api/students	Create student
PUT	/api/students/{id}	Update student
DELETE	/api/students/{id}	Delete student
⚙️ Configuration & Run
1️⃣ Configure Database

Update in application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/SMS
spring.datasource.username=postgres
spring.datasource.password=Shiva123

2️⃣ Run Application

Run DemoApplication.java

Backend runs on:

http://localhost:8080

🎯 Purpose of the Project

This project was developed to gain strong hands-on experience in:

Spring Boot backend development

JWT-based authentication

REST API design

Clean layered architecture

Secure full-stack applications
