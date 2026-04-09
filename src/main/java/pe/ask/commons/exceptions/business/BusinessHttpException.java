package pe.ask.commons.exceptions.business;

import pe.ask.commons.exceptions.model.BaseException;
import pe.ask.commons.exceptions.model.ErrorDefinition;
import pe.ask.commons.exceptions.utils.BaseExceptionBuilder;
import java.util.Map;

/**
 * Exception specialized for business logic errors that need to be mapped to HTTP responses.
 * <p>
 * Use this class to represent functional errors (e.g., "Insufficient Funds", "User Already Exists")
 * that occur within the business layer of the application.
 */
public class BusinessHttpException extends BaseException {

    /**
     * Internal constructor used by the Builder.
     *
     * @param errorCode the unique error identifier.
     * @param title     the brief summary of the error.
     * @param message   the detailed error description.
     * @param status    the HTTP status code.
     * @param errors    a map of specific error details.
     * @param cause     the underlying cause of the exception.
     */
    protected BusinessHttpException(String errorCode, String title, String message, int status, Map<String, String> errors, Throwable cause) {
        super(errorCode, title, message, status, errors, cause);
    }

    /**
     * Constructs a new business exception using a predefined error definition.
     *
     * @param def the error definition containing code, title, message, and status.
     */
    public BusinessHttpException(ErrorDefinition def) {
        super(def.getErrorCode(), def.getTitle(), def.getMessage(), def.getStatus(), null, null);
    }

    /**
     * Constructs a new business exception using a predefined error definition and a cause.
     *
     * @param def   the error definition containing code, title, message, and status.
     * @param cause the underlying cause of this exception.
     */
    public BusinessHttpException(ErrorDefinition def, Throwable cause) {
        super(def.getErrorCode(), def.getTitle(), def.getMessage(), def.getStatus(), null, cause);
    }

    /**
     * Creates a new fluent builder to customize the exception instance.
     *
     * @return a new {@link Builder} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Concrete builder for {@link BusinessHttpException}.
     * <p>
     * Inherits all fluent methods from {@link BaseExceptionBuilder} and
     * implements the build logic for this specific exception type.
     */
    public static class Builder extends BaseExceptionBuilder<Builder, BusinessHttpException> {

        /**
         * Default constructor for the BusinessHttpException builder.
         */
        public Builder() {
            super();
        }

        /**
         * Assembles the parameters into a new {@code BusinessHttpException} instance.
         *
         * @return a fully initialized {@link BusinessHttpException}.
         */
        @Override
        public BusinessHttpException build() {
            return new BusinessHttpException(errorCode, title, message, status, errors, cause);
        }
    }
}
