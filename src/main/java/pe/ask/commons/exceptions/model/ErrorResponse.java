package pe.ask.commons.exceptions.model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * A Data Transfer Object (DTO) used to represent a standardized error structure in API responses.
 * <p>
 * This record captures all relevant details from a {@link BaseException} to provide
 * consistent error feedback to API consumers (mobile apps, frontends, or other services).
 *
 * @param errorCode A unique string identifying the specific error type.
 * @param title     A brief, human-readable summary of the error.
 * @param message   A detailed explanation of what went wrong.
 * @param status    The HTTP status code associated with the error.
 * @param timestamp The exact date and time when the error was captured.
 * @param errors    A map containing field-level validation details or additional metadata.
 */
public record ErrorResponse(
        String errorCode,
        String title,
        String message,
        int status,
        LocalDateTime timestamp,
        Map<String, String> errors
) {
    /**
     * Factory method to create an {@code ErrorResponse} directly from a {@link BaseException}.
     * <p>
     * This method extracts the internal state of the exception and maps it to this
     * public-facing data structure.
     *
     * @param ex the exception to be converted.
     * @return a new {@link ErrorResponse} instance populated with the exception's data.
     */
    public static ErrorResponse from(BaseException ex) {
        return new ErrorResponse(
                ex.getErrorCode(),
                ex.getTitle(),
                ex.getMessage(),
                ex.getStatus(),
                ex.getTimestamp(),
                ex.getErrors()
        );
    }
}