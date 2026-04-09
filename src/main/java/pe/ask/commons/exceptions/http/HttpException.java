package pe.ask.commons.exceptions.http;

import pe.ask.commons.exceptions.model.BaseException;
import pe.ask.commons.exceptions.model.ErrorDefinition;
import pe.ask.commons.exceptions.utils.BaseExceptionBuilder;
import java.util.Map;

/**
 * Exception specialized for infrastructure and external communication errors.
 * <p>
 * This class should be used to represent failures occurring when interacting with
 * external services, APIs, or general network-level issues (e.g., "Bad Gateway",
 * "Service Unavailable", "External API Error").
 */
public class HttpException extends BaseException {

    /**
     * Internal constructor used by the Builder to instantiate the exception.
     *
     * @param errorCode the unique identifier for the specific HTTP error.
     * @param title     the brief summary of the communication failure.
     * @param message   the detailed explanation of the network or service error.
     * @param status    the HTTP status code (typically in the 5xx or 4xx range).
     * @param errors    a map containing additional metadata or downstream error details.
     * @param cause     the original exception that triggered this error (e.g., a SocketTimeout).
     */
    protected HttpException(String errorCode, String title, String message, int status, Map<String, String> errors, Throwable cause) {
        super(errorCode, title, message, status, errors, cause);
    }

    /**
     * Constructs a new HTTP exception using a predefined error definition.
     *
     * @param def the predefined error definition (usually from an Enum).
     */
    public HttpException(ErrorDefinition def) {
        super(def.getErrorCode(), def.getTitle(), def.getMessage(), def.getStatus(), null, null);
    }

    /**
     * Constructs a new HTTP exception using a predefined error definition and a cause.
     *
     * @param def   the predefined error definition (usually from an Enum).
     * @param cause the underlying cause of this communication failure.
     */
    public HttpException(ErrorDefinition def, Throwable cause) {
        super(def.getErrorCode(), def.getTitle(), def.getMessage(), def.getStatus(), null, cause);
    }

    /**
     * Creates a new fluent builder to customize the HTTP exception instance.
     *
     * @return a new {@link Builder} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Concrete builder for {@link HttpException}.
     * <p>
     * Provides a fluent API to set all attributes inherited from {@link BaseExceptionBuilder}
     * specifically for generating {@code HttpException} instances.
     */
    public static class Builder extends BaseExceptionBuilder<Builder, HttpException> {

        /**
         * Default constructor for the HttpException builder.
         */
        public Builder() {
            super();
        }

        /**
         * Assembles the parameters into a new {@code HttpException} instance.
         *
         * @return a fully initialized {@link HttpException}.
         */
        @Override
        public HttpException build() {
            return new HttpException(errorCode, title, message, status, errors, cause);
        }
    }
}
