# CompanyWebAppNew

A modern Java web application for company project and client management with a responsive landing page and admin panel.

## Features

- **Landing Page**: Dynamic display of projects and clients, contact form, newsletter subscription
- **Admin Panel**: Add projects/clients with image upload and cropping, view contacts and subscribers
- **Image Cropping**: Built-in image cropping functionality using Cropper.js
- **Responsive Design**: Mobile-friendly interface
- **Security**: Input validation, SQL injection prevention, XSS protection

## Prerequisites

- Java 8 or higher
- Apache Tomcat 10.1.41
- MySQL 8.0+
- Maven 3.6+

## Setup Instructions

### 1. Database Setup

Create a MySQL database and tables:

```sql
CREATE DATABASE companywebappnew;
USE companywebappnew;

CREATE TABLE projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    image_path VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    designation VARCHAR(100) NOT NULL,
    image_path VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile VARCHAR(15) NOT NULL,
    city VARCHAR(30) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE newsletter (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 2. Configuration

Update `src/main/resources/db.properties` with your database credentials:

```properties
db.url=jdbc:mysql://localhost:3306/companywebappnew
db.username=your_username
db.password=your_password
```

### 3. Build and Deploy

```bash
# Clean and build
mvn clean package

# Deploy to Tomcat
# Copy target/CompanyWebAppNew.war to Tomcat webapps directory
```

### 4. Access the Application

- **Landing Page**: `http://localhost:8080/CompanyWebAppNew/`
- **Admin Dashboard**: `http://localhost:8080/CompanyWebAppNew/admin/dashboard.jsp`

## Security Features

### Input Validation
- All form inputs are validated on both client and server side
- Email format validation using regex patterns
- Mobile number validation (10 digits)
- File type and size restrictions for uploads

### SQL Injection Prevention
- All database queries use PreparedStatement
- Parameterized queries prevent SQL injection attacks

### XSS Protection
- HTML escaping in JavaScript for dynamic content
- Input sanitization before database storage

### File Upload Security
- File type validation (JPG, JPEG, PNG, GIF only)
- File size limits (5MB max)
- Unique filename generation to prevent conflicts
- Path traversal protection

## Code Quality Improvements

### Backend (Java)
- ✅ Proper exception handling with logging
- ✅ Input validation and sanitization
- ✅ PreparedStatement for all database queries
- ✅ File upload security measures
- ✅ Consistent error responses
- ✅ WebServlet annotations for all servlets

### Frontend (JavaScript/CSS)
- ✅ Error handling for fetch requests
- ✅ XSS prevention with HTML escaping
- ✅ Responsive design with mobile support
- ✅ Modular CSS with proper organization
- ✅ Accessibility improvements

### General
- ✅ Consistent coding style
- ✅ Meaningful variable names
- ✅ Proper documentation
- ✅ Separation of concerns

## File Structure

```
CompanyWebAppNew/
├── src/main/
│   ├── java/com/companywebappnew/
│   │   ├── DBConnection.java
│   │   └── servlets/
│   │       ├── AddClientServlet.java
│   │       ├── AddProjectServlet.java
│   │       ├── ClientServlet.java
│   │       ├── ContactServlet.java
│   │       ├── NewsletterServlet.java
│   │       └── ProjectServlet.java
│   ├── resources/
│   │   └── db.properties
│   └── webapp/
│       ├── admin/
│       │   ├── addClient.jsp
│       │   ├── addProject.jsp
│       │   ├── dashboard.jsp
│       │   ├── viewContacts.jsp
│       │   └── viewSubscribers.jsp
│       ├── assets/
│       │   ├── css/
│       │   │   ├── style.css
│       │   │   └── cropper.min.css
│       │   ├── js/
│       │   │   ├── main.js
│       │   │   ├── project-cropper.js
│       │   │   ├── client-cropper.js
│       │   │   └── cropper.min.js
│       │   └── images/
│       ├── index.jsp
│       └── navbar.jsp
└── pom.xml
```

## Security Recommendations

### Production Deployment
1. **Database Security**
   - Use environment variables for database credentials
   - Create a dedicated database user with minimal privileges
   - Enable SSL for database connections

2. **Application Security**
   - Deploy behind a reverse proxy (nginx/Apache)
   - Enable HTTPS with valid SSL certificate
   - Implement rate limiting
   - Add CSRF protection

3. **File Upload Security**
   - Store uploaded files outside web root
   - Implement virus scanning for uploads
   - Use cloud storage (AWS S3, etc.) for production

4. **Authentication & Authorization**
   - Implement admin login/logout
   - Use session management
   - Add role-based access control

## Troubleshooting

### Common Issues

1. **404 Errors for Servlets**
   - Ensure all servlets have `@WebServlet` annotations
   - Check Tomcat deployment assembly includes Maven Dependencies

2. **Database Connection Issues**
   - Verify MySQL service is running
   - Check database credentials in `db.properties`
   - Ensure MySQL JDBC driver is in classpath

3. **Image Upload Issues**
   - Check file permissions on upload directory
   - Verify file size limits in `@MultipartConfig`
   - Ensure Cropper.js files are accessible

4. **CSS/JS Not Loading**
   - Check browser network tab for 404 errors
   - Verify file paths in HTML
   - Clear browser cache

## Contributing

1. Follow the existing code style
2. Add proper error handling
3. Include input validation
4. Test thoroughly before submitting
5. Update documentation as needed

## License

This project is for educational purposes. Use at your own risk in production environments. 