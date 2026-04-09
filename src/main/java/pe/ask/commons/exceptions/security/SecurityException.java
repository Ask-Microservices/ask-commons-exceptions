package pe.ask.commons.exceptions.security;

import pe.ask.commons.exceptions.model.BaseException;
import pe.ask.commons.exceptions.model.ErrorDefinition;
import pe.ask.commons.exceptions.utils.BaseExceptionBuilder;
import java.util.Map;

/**
 * Exception specialized for authentication and authorization failures.
 * <p>
 * Use this class to represent security-related issues such as "Invalid Token",
 * "Expired Session", or "Insufficient Permissions" (typically mapping to HTTP 401 or 403).
 */
public class SecurityException extends BaseException {

    /**
     * Internal constructor used by the Builder to instantiate the exception.
     *
     * @param errorCode the unique identifier for the specific security error.
     * @param title     the brief summary of the security violation.
     * @param message   the detailed explanation of the access failure.
     * @param status    the HTTP status code (typically 401 Unauthorized or 403 Forbidden).
     * @param errors    a map containing additional security context or metadata.
     * @param cause     the underlying cause of the security exception.
     */
    protected SecurityException(String errorCode, String title, String message, int status, Map<String, String> errors, Throwable cause) {
        super(errorCode, title, message, status, errors, cause);
    }

    /**
     * Constructs a new security exception using a predefined error definition.
     *
     * @param def the predefined error definition (usually from a security-level Enum).
     */
    public SecurityException(ErrorDefinition def) {
        super(def.getErrorCode(), def.getTitle(), def.getMessage(), def.getStatus(), null, null);
    }

    /**
     * Constructs a new security exception using a predefined error definition and a cause.
     *
     * @param def   the predefined error definition.
     * @param cause the underlying cause of this security failure.
     */
    public SecurityException(ErrorDefinition def, Throwable cause) {
        super(def.getErrorCode(), def.getTitle(), def.getMessage(), def.getStatus(), null, cause);
    }

    /**
     * Creates a new fluent builder to customize the security exception instance.
     *
     * @return a new {@link Builder} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Concrete builder for {@link SecurityException}.
     * <p>
     * Provides a fluent API to define security breach details and
     * assemble them into a {@code SecurityException} instance.
     */
    public static class Builder extends BaseExceptionBuilder<Builder, SecurityException> {

        /**
         * Default constructor for the SecurityException builder.
         */
        public Builder() {
            super();
        }

        /**
         * Assembles the parameters into a new {@code SecurityException} instance.
         *
         * @return a fully initialized {@link SecurityException}.
         */
        @Override
        public SecurityException build() {
            return new SecurityException(errorCode, title, message, status, errors, cause);
        }
    }
}
