# Microservices Project: MS-User & MS-Notification

Welcome to the microservices project, the third and final challenge of the internship Java and Spring Boot at Compass UOL!

## Technologies Used
<div style="display: inline_block"><br/>    
     <img align="center" alt="java" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
     <img align="center" alt="html5" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" /> 
     <img align="center" alt="html5" src="https://img.shields.io/badge/rabbitmq-%23FF6600.svg?&style=for-the-badge&logo=rabbitmq&logoColor=white" /> 
     <img align="center" alt="html5" src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" /> 
</div><br>

## Running the Projects
To execute the MS-User and MS-Notification projects, you have a few options based on your preference and setup:

### Using an IDE (IntelliJ, Eclipse, etc.):
- Open the project in your preferred IDE.
 - Ensure you have RabbitMQ and MySQL installed locally (or you can use Docker) or adjust the application.properties file to connect to your remote instances.
 - Run the MSUserApplication class to start the MS-User service.
 - Run the MSNotificationApplication class to start the MS-Notification service.

 ### Using Postman:

1. Open Postman.

2. Import the provided Postman collection for MS-User and MS-Notification.

3. Test the following endpoints:
   - **MS-User Endpoints:**
     - Create User: `POST /v1/users`
     - User Login: `POST /v1/login`
     - Get User by ID: `GET /v1/users/{id}`
     - Update User: `PUT /v1/users/{id}`
     - Update User Password: `PUT /v1/users/{id}/password`

   - **MS-Notification Endpoint:**
     - Get Notifications: `GET /v1/notifications`

4. Ensure that the MS-User and MS-Notification services are running.

5. Execute the requests and verify the responses.

# ms-user
The ms-user is a microservice responsible for managing user-related operations. It includes functionality for user creation, login, getting, and updates. Additionally, the service provides exception handling for various scenarios, such as invalid data and data integrity violations.

## Endpoints
1. **Create User**
   - Endpoint: `POST /v1/users`
   - Description: Creates a new user based on the provided user data.
   - Request Body: `UserRequestDto`
   - Response: `UserResponseDto`

2. **User Login**
   - Endpoint: `POST /v1/login`
   - Description: Authenticates a user based on the provided email and password.
   - Request Body: `AuthenticationDto`
   - Response: `LoginResponseDTO`

3. **Get User by ID**
   - Endpoint: `GET /v1/users/{id}`
   - Description: Retrieves user details based on the provided user ID.
   - Path Variable: `id` (User ID)
   - Response: `UserResponseDto`

4. **Update User**
   - Endpoint: `PUT /v1/users/{id}`
   - Description: Updates user details based on the provided user ID and user data.
   - Path Variable: `id` (User ID)
   - Request Body: `UserRequestDto`
   - Response: `UserResponseDto`

5. **Update User Password**
   - Endpoint: `PUT /v1/users/{id}/password`
   - Description: Updates the password for the user with the specified ID.
   - Path Variable: `id` (User ID)
   - Request Body: `String` (New Password)
   - Response: `UserResponseDto`


## Model

### Entity

#### User
Entity class representing user details, including fields such as userId, firstName, lastName, cpf, birthdate, email, password, active, and role.

#### EventNotification
Entity class representing event notifications with fields for event type, email, and date.

### DTOs

#### AuthenticationDto
Represents the user authentication data.

#### LoginResponseDTO
Represents the response DTO for user login, including the authentication token.

#### UserRequestDto
DTO for user creation and update requests.

#### UserResponseDto
DTO for representing user details in responses.

### Enums

#### Event
Enumeration representing different events such as CREATE, UPDATE, LOGIN, and UPDATE_PASSWORD.

#### UserRole
Enumeration representing user roles (USER, ADMIN).

## Services

#### NotificationServiceImpl
Service responsible for sending event notifications using RabbitMQ.

#### UserServiceImpl
Implementation of the UserService interface, providing user-related operations such as creation, login, retrieval, and updates.

## Repository

#### UserRepository
JPA repository for interacting with the database, providing methods for finding users by CPF or email.


# ms-notification
The ms-notification service is responsible for receiving event notifications from the MSUser service via RabbitMQ and storing them in a MySQL database.

# Accessing Received Notifications

To retrieve the notifications received from the RabbitMQ queue `event_user`, you can use the following endpoint in the ms-notification project:

### Endpoint:
GET /v1/notifications

### Example response
```json
[
  {
    "event": "LOGIN",
    "email": "mari2@example.com",
    "date": "2023-11-13T18:28:55.338267500"
  },
  {
    "event": "UPDATE_PASSWORD",
    "email": "mari2@example.com",
    "date": "2023-11-13T18:30:25.514020400"
  }
]


