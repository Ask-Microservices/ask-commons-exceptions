package pe.ask.commons.exceptions.model;

/**
 * Contract defining the mandatory structure for error constants.
 * <p>
 * Implementing this interface (typically via Enums) allows for standardized
 * error definitions that can be passed directly to exception constructors
 * and builders throughout the Ask Commons ecosystem.
 */
public interface ErrorDefinition {

    /**
     * Gets the unique identification code for the error.
     * @return a {@link String} representing the error code (e.g., "SEC-401").
     */
    String getErrorCode();

    /**
     * Gets a brief, human-readable title of the error.
     * @return a {@link String} containing the error summary.
     */
    String getTitle();

    /**
     * Gets the detailed description or template of the error message.
     * @return a {@link String} explaining the error in detail.
     */
    String getMessage();

    /**
     * Gets the associated HTTP status code.
     * @return an {@code int} representing the HTTP status (e.g., 400, 404, 500).
     */
    int getStatus();
}