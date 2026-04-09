# Ask Commons - Exceptions

A robust and flexible library for standardizing exception handling in Java applications, particularly REST APIs. It provides a structured framework for defining, creating, and representing errors consistently.

## Core Concepts

This library is built around a few key components that work together to provide a powerful exception handling system:

- **`BaseException`**: The abstract cornerstone of all custom exceptions. It standardizes error reporting by including an error code, title, message, HTTP status, and a timestamp.

- **`ErrorDefinition`**: A contract (interface) that enforces a standard for error constants. Typically implemented by an `enum`, it allows you to create a centralized catalog of all possible errors in your application.

- **`HttpException` & `BusinessHttpException`**: Specialized exception types for infrastructure/network errors and business logic failures, respectively.

- **`BaseExceptionBuilder`**: A fluent builder that allows for the easy construction of complex and customized exception instances.

- **`ErrorResponse`**: A DTO (`record`) designed to represent a standardized error structure in API responses, making it simple to serialize exceptions into a consistent JSON format.

## Usage

### 1. Define Your Error Catalog

Create an `enum` that implements the `ErrorDefinition` interface. This will serve as your single source of truth for all defined errors.

```java
import pe.ask.commons.exceptions.model.ErrorDefinition;

public enum MyErrorDefs implements ErrorDefinition {
    USER_NOT_FOUND("ERR-404", "User Not Found", "The requested user does not exist.", 404),
    INSUFFICIENT_FUNDS("BIZ-001", "Insufficient Funds", "The account has insufficient funds for this transaction.", 400),
    EXTERNAL_API_FAILURE("SYS-503", "External Service Error", "The external payment gateway is unavailable.", 503);

    private final String errorCode;
    private final String title;
    private final String message;
    private final int status;

    MyErrorDefs(String errorCode, String title, String message, int status) {
        this.errorCode = errorCode;
        this.title = title;
        this.message = message;
        this.status = status;
    }

    @Override
    public String getErrorCode() { return errorCode; }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getMessage() { return message; }

    @Override
    public int getStatus() { return status; }
}
```

### 2. Throw Standardized Exceptions

In your application logic, throw exceptions using the definitions from your error catalog.

**For Business Logic Errors:**

```java
import pe.ask.commons.exceptions.business.BusinessHttpException;

public void withdraw(double amount) {
    if (balance < amount) {
        throw new BusinessHttpException(MyErrorDefs.INSUFFICIENT_FUNDS);
    }
    // ...
}
```

**For HTTP/Infrastructure Errors:**

```java
import pe.ask.commons.exceptions.http.HttpException;

public void callExternalService() {
    try {
        // ... call external API
    } catch (Exception e) {
        throw new HttpException(MyErrorDefs.EXTERNAL_API_FAILURE, e);
    }
}
```

### 3. Use the Fluent Builder for Custom Exceptions

For more complex scenarios, use the fluent builder to customize the exception on the fly.

```java
import pe.ask.commons.exceptions.business.BusinessHttpException;
import java.util.Map;

public void updateUser(String userId, UserData data) {
    if (isUsernameTaken(data.getUsername())) {
        throw BusinessHttpException.builder()
            .from(MyErrorDefs.VALIDATION_ERROR) // Assuming a generic validation error definition
            .message("The username is already taken.")
            .errors(Map.of("username", "This username is not available."))
            .build();
    }
    // ...
}
```

### 4. Global Exception Handling (Example with Spring Boot)

To automatically convert these exceptions into standardized JSON API responses, implement a global exception handler.

```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pe.ask.commons.exceptions.model.BaseException;
import pe.ask.commons.exceptions.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        ErrorResponse errorResponse = ErrorResponse.from(ex);
        return new ResponseEntity<>(errorResponse, null, ex.getStatus());
    }
}
```

When an exception is thrown, the API will now automatically produce a response like this:

```json
{
  "errorCode": "ERR-404",
  "title": "User Not Found",
  "message": "The requested user does not exist.",
  "status": 404,
  "timestamp": "2023-10-27T10:30:00.123456",
  "errors": {}
}
```

## Installation

To include this library in your project, add the following dependency to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("pe.ask.commons:ask-commons-exceptions:1.0.0")
}
```
*(Note: Adjust the version number as needed.)*

## Project Structure

- `pe.ask.commons.exceptions.model`: Contains the core data models (`BaseException`, `ErrorDefinition`, `ErrorResponse`).
- `pe.ask.commons.exceptions.http`: Contains exceptions for HTTP and infrastructure-related errors (`HttpException`).
- `pe.ask.commons.exceptions.business`: Contains exceptions for business logic errors (`BusinessHttpException`).
- `pe.ask.commons.exceptions.security`: Contains exceptions for security-related errors (`SecurityException`).
- `pe.ask.commons.exceptions.runtime`: Contains a generic custom runtime exception (`CustomRuntimeException`).
- `pe.ask.commons.exceptions.utils`: Contains the fluent exception builder (`BaseExceptionBuilder`).

---

This library promotes clean, maintainable, and consistent error handling, improving the developer experience and the reliability of your API for consumers.
