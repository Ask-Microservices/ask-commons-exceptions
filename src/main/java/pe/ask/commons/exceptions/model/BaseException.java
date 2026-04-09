package pe.ask.commons.exceptions.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

/**
 * Base abstract class for all custom exceptions within the Ask Commons ecosystem.
 * <p>
 * This class extends {@link RuntimeException} and provides a standardized structure
 * for error reporting, including error codes, titles, HTTP status codes, and
 * metadata like timestamps and field-specific errors.
 */
public abstract class BaseException extends RuntimeException {

    /** Specific identification code for the error (e.g., "ERR-001"). */
    private final String errorCode;

    /** A short, human-readable summary of the error. */
    private final String title;

    /** Detailed explanation of the error occurrence. */
    private final String message;

    /** The HTTP status code equivalent for this error (e.g., 404, 500). */
    private final int status;

    /** The exact moment the exception was instantiated. */
    private final LocalDateTime timestamp;

    /** A map containing field-specific validation errors or additional metadata. */
    private final Map<String, String> errors;

    /**
     * Constructs a new exception using a predefined error definition.
     *
     * @param def the error definition containing code, title, message, and status.
     */
    protected BaseException(ErrorDefinition def) {
        this(def.getErrorCode(), def.getTitle(), def.getMessage(), def.getStatus(), null, null);
    }

    /**
     * Constructs a new exception using a predefined error definition and a cause.
     *
     * @param def   the error definition containing code, title, message, and status.
     * @param cause the underlying cause of this exception.
     */
    protected BaseException(ErrorDefinition def, Throwable cause) {
        this(def.getErrorCode(), def.getTitle(), def.getMessage(), def.getStatus(), null, cause);
    }

    /**
     * Full constructor for total customization of the exception state.
     *
     * @param errorCode the unique error identifier.
     * @param title     the brief summary of the error.
     * @param message   the detailed error description.
     * @param status    the HTTP status code.
     * @param errors    a map of specific error details (null becomes an empty map).
     * @param cause     the underlying cause of the exception.
     */
    protected BaseException(String errorCode, String title, String message, int status, Map<String, String> errors, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.title = title;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors = (errors != null) ? Map.copyOf(errors) : Collections.emptyMap();
    }

    /**
     * Retrieves the unique error identification code.
     * @return the error code string.
     */
    public String getErrorCode() { return errorCode; }

    /**
     * Retrieves the short summary title of the error.
     * @return the error title.
     */
    public String getTitle() { return title; }

    /**
     * Retrieves a detailed description of the error.
     * @return the detailed error message.
     */
    @Override
    public String getMessage() { return message; }

    /**
     * Retrieves the associated HTTP status code.
     * @return the status code integer.
     */
    public int getStatus() { return status; }

    /**
     * Retrieves the timestamp when the error occurred.
     * @return the local date time of the incident.
     */
    public LocalDateTime getTimestamp() { return timestamp; }

    /**
     * Retrieves an unmodifiable map of error details.
     * @return a map of additional error information.
     */
    public Map<String, String> getErrors() { return errors; }
}
