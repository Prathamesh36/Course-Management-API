

## API Documentation Link (POSTMAN) : 
https://documenter.getpostman.com/view/33494980/2sA3XMjiyp

## Project Description
This project is a Course Management System API for a Learning Management System (LMS), developed using Spring Boot. It provides RESTful endpoints to manage users (students and teachers), courses, lessons, and track progress. The API supports user authentication and authorization using JWT tokens, ensuring secure access to the endpoints. The system facilitates CRUD operations on courses and lessons, progress tracking for students, and incorporates robust error handling and validation mechanisms.

## Key Features
1. User Authentication and Authorization:

  - Register and login endpoints for users.
  - Role-based access control (student and teacher roles).
  - Secure endpoints using JWT tokens.
   
2. Course Management:

  - CRUD operations for courses:
     - POST /courses: Create a new course (teachers only).
     - GET /courses: Retrieve a list of all courses.
     - GET /courses/{id}: Retrieve details of a specific course.
     - PUT /courses/{id}: Update a course (teachers only).
     - DELETE /courses/{id}: Delete a course (teachers only).

3. Lesson Management:

   - CRUD operations for lessons associated with courses.
   - Managed relationships between courses and lessons.
    
4. Progress Tracking:

   - Retrieve and update progress for students.
   - Track individual progress of students in various courses.

5. Database Management:

   - Uses a relational database (e.g., MySQL) to store and manage data.
   - Defines schemas and relationships for users, courses, lessons, and progress tracking.

6. Validation and Error Handling:

   - Comprehensive data validation for all endpoints.
   - Global exception handling for meaningful error responses.
   - Proper HTTP status codes for different types of responses.

7. Pagination and Sorting:

   - Supports pagination and sorting for course listings.
   - Allows customizable page size and sorting parameters.

## Technical Details
   - Spring Boot Framework: For building the RESTful API.
   - Spring Security: For authentication and authorization using JWT.
   - Hibernate/JPA: For ORM and database interaction.
   - MySQL: As the relational database to store the data.
   - Maven: For project management and dependencies.
   - DTOs (Data Transfer Objects): For transferring data between the server and client, ensuring separation of concerns.

This API provides a robust foundation for a Course Management System in an LMS, ensuring secure and efficient management of courses, lessons, and student progress.


