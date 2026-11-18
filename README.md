# JWT Authentication System

A RESTful authentication and authorization system built with **Spring Boot** and **Spring Security**, using **JSON Web Tokens (JWT)** to secure API endpoints.

## 1. Project Overview

This project implements a secure authentication system that allows users to register, log in, and access protected resources using JWT-based authentication.

After successful login, the application generates a JWT token that the client uses to authenticate subsequent requests.

The project is designed to support role-based access control, allowing different levels of access for normal users and administrators.

## 2. Features

- User registration
- User login and authentication
- Password encryption using BCrypt
- JWT token generation and validation
- Stateless authentication
- Protected REST API endpoints
- Role-based authorization (`ROLE_USER` and `ROLE_ADMIN`)
- User profile access
- Administrator dashboard and user management
- Secure password storage
- MySQL database integration

## 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Spring Boot | Backend framework |
| Spring Security | Authentication and authorization |
| Spring Data JPA | Database operations |
| MySQL | Relational database |
| JWT (JSON Web Token) | Token-based authentication |
| Maven | Dependency management |
| Lombok | Reducing boilerplate code |
| Postman | API testing |

## 4. Project Structure

```text
AuthenticationSystem/
│
├── src/
│   └── main/
│       ├── java/com/deviron/AuthenticationSystem/
│       │
│       ├── Controller/
│       │   ├── AuthController.java
│       │   └── AdminController.java
│       │
│       ├── DTO/
│       │   ├── RegisterDTO.java
│       │   ├── LoginDTO.java
│       │   ├── AuthResponseDTO.java
│       │   ├── UserResponseDTO.java
│       │   └── RoleUpdateDTO.java
│       │
│       ├── Entity/
│       │   └── User.java
│       │
│       ├── Repository/
│       │   └── UserRepo.java
│       │
│       ├── Security/
│       │   ├── JwtService.java
│       │   ├── JwtAuthFilter.java
│       │   └── SecurityConfig.java
│       │
│       ├── Service/
│       │   ├── AuthService.java
│       │   └── IMPL/
│       │       └── AuthServiceImpl.java
│       │
│       └── AuthenticationSystemApplication.java
│
├── src/main/resources/
│   └── application.properties
│
├── pom.xml
└── README.md
```

## 5. Getting Started

### Prerequisites

Make sure you have installed:

- Java JDK
- IntelliJ IDEA or another Java IDE
- MySQL Server
- Maven
- Postman

### Step 1: Clone the Repository

```bash
git clone <YOUR_REPOSITORY_URL>
```

Navigate into the project directory:

```bash
cd AuthenticationSystem
```

### Step 2: Create the Database

Open MySQL and execute:

```sql
CREATE DATABASE authenticationdb;
```

### Step 3: Configure Database Connection

Open `src/main/resources/application.properties` and configure your MySQL credentials.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/authenticationdb
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=YOUR_BASE64_ENCODED_SECRET_KEY
jwt.expiration=86400000
```

**Important:** Replace the database credentials and JWT secret with your own values. Use a strong, randomly generated Base64-encoded secret key and do not commit real credentials or secrets to GitHub.

### Step 4: Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Alternatively, run `AuthenticationSystemApplication.java` from your IDE.

The application will start on:

```text
http://localhost:8080
```

## 6. API Endpoints

### Authentication Endpoints

| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/api/auth/register` | Register a new user | Public |
| POST | `/api/auth/login` | Authenticate a user | Public |
| GET | `/api/auth/profile` | Retrieve authenticated profile | Authenticated |

### Administrator Endpoints

| Method | Endpoint | Description | Access |
|---|---|---|---|
| GET | `/api/admin/dashboard` | Admin dashboard | Admin |
| GET | `/api/admin/users` | Retrieve all users | Admin |
| GET | `/api/admin/users/{id}` | Retrieve a user by ID | Admin |
| DELETE | `/api/admin/users/{id}` | Delete a user | Admin |
| PUT | `/api/admin/users/{id}/role` | Update a user's role | Admin |

## 7. Testing with Postman

### Register a User

**POST**

```text
http://localhost:8080/api/auth/register
```

Example request body:

```json
{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123"
}
```

### Login

**POST**

```text
http://localhost:8080/api/auth/login
```

Example request body:

```json
{
    "email": "john@example.com",
    "password": "password123"
}
```

The response contains an authentication token.

### Access a Protected Endpoint

**GET**

```text
http://localhost:8080/api/auth/profile
```

In Postman:

1. Open the Authorization tab.
2. Select Bearer Token.
3. Paste the JWT token received during login.
4. Send the request.

## 8. Authentication Flow

1. A user submits registration details.
2. The application encrypts the password using BCrypt.
3. User information is saved in MySQL.
4. The user submits login credentials.
5. Spring Security verifies the credentials.
6. The application generates a signed JWT token.
7. The client sends the token with subsequent API requests.
8. The JWT filter validates the token and retrieves the user's current role.
9. Spring Security grants or denies access according to the user's permissions.

## 9. Role-Based Access Control

The system supports two primary roles:

| Role | Permissions |
|---|---|
| ROLE_USER | Access normal authenticated user resources |
| ROLE_ADMIN | Access administrator resources and normal user resources |

New registrations should receive the `ROLE_USER` role by default. Administrator privileges should only be assigned through a controlled administrative process.

## 10. Security Considerations

- Passwords are stored as BCrypt hashes.
- JWT tokens are signed and validated before use.
- Protected endpoints require authentication.
- Authorization is based on the user's current database role.
- Database credentials and JWT secrets should not be exposed publicly.
- HTTPS should be used when deploying the application.

## 11. Future Improvements

- Refresh token implementation
- Password reset functionality
- Email verification
- Account activation and deactivation
- Login attempt limiting
- Improved exception handling
- API documentation using Swagger/OpenAPI
- Automated unit and integration testing

## 12. Author

**Developer:** Deviron

**Project:** JWT Authentication System

**Framework:** Spring Boot

---

*This project is intended for learning and demonstrating secure REST API authentication and authorization using Spring Boot and Spring Security.*
