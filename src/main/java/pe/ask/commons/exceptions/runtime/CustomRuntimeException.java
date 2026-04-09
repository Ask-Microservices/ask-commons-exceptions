package pe.ask.commons.exceptions.runtime;

import pe.ask.commons.exceptions.model.BaseException;
import pe.ask.commons.exceptions.model.ErrorDefinition;
import pe.ask.commons.exceptions.utils.BaseExceptionBuilder;
import java.util.Map;

/**
 * Exception specialized for unexpected system or technical runtime errors.
 * <p>
 * This class should be used to wrap low-level technical failures, configuration issues,
 * or internal logic inconsistencies that do not fall under business rules or
 * external HTTP integrations (e.g., "Internal Server Error", "Configuration Missing").
 */
public class CustomRuntimeException extends BaseException {

    /**
     * Internal constructor used by the Builder to instantiate the exception.
     *
     * @param errorCode the unique identifier for the specific system error.
     * @param title     the brief summary of the technical failure.
     * @param message   the detailed explanation of the internal error.
     * @param status    the HTTP status code (typically 500 Internal Server Error).
     * @param errors    a map containing additional debugging metadata or system state.
     * @param cause     the original exception that triggered this technical failure.
     */
    protected CustomRuntimeException(String errorCode, String title, String message, int status, Map<String, String> errors, Throwable cause) {
        super(errorCode, title, message, status, errors, cause);
    }

    /**
     * Constructs a new runtime exception using a predefined error definition.
     *
     * @param def the predefined error definition (usually from a system-level Enum).
     */
    public CustomRuntimeException(ErrorDefinition def) {
        super(def.getErrorCode(), def.getTitle(), def.getMessage(), def.getStatus(), null, null);
    }

    /**
     * Constructs a new runtime exception using a predefined error definition and a cause.
     *
     * @param def   the predefined error definition.
     * @param cause the underlying technical cause of this exception.
     */
    public CustomRuntimeException(ErrorDefinition def, Throwable cause) {
        super(def.getErrorCode(), def.getTitle(), def.getMessage(), def.getStatus(), null, cause);
    }

    /**
     * Creates a new fluent builder to customize the runtime exception instance.
     *
     * @return a new {@link Builder} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Concrete builder for {@link CustomRuntimeException}.
     * <p>
     * Provides a fluent API to define technical error details and
     * assemble them into a {@code CustomRuntimeException} instance.
     */
    public static class Builder extends BaseExceptionBuilder<Builder, CustomRuntimeException> {

        /**
         * Default constructor for the CustomRuntimeException builder.
         */
        public Builder() {
            super();
        }

        /**
         * Assembles the parameters into a new {@code CustomRuntimeException} instance.
         *
         * @return a fully initialized {@link CustomRuntimeException}.
         */
        @Override
        public CustomRuntimeException build() {
            return new CustomRuntimeException(errorCode, title, message, status, errors, cause);
        }
    }
}
