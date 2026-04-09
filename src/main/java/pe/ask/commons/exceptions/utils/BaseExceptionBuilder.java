package pe.ask.commons.exceptions.utils;

import pe.ask.commons.exceptions.model.ErrorDefinition;
import java.util.Map;

/**
 * Abstract base builder for creating fluently configured exception instances.
 * <p>
 * This builder uses recursive generics to ensure that fluent method chaining
 * returns the specific builder subtype (B) rather than the base class.
 *
 * @param <B> the type of the builder implementation (Self-reference).
 * @param <E> the type of the exception to be built.
 */
public abstract class BaseExceptionBuilder<B extends BaseExceptionBuilder<B, E>, E> {

    /**
     * Default constructor for the base exception builder.
     */
    protected BaseExceptionBuilder() {
    }

    /** The unique identification code for the error. */
    protected String errorCode;

    /** A short summary of the error. */
    protected String title;

    /** The detailed description of the error. */
    protected String message;

    /** The associated HTTP status code. */
    protected int status;

    /** The underlying cause of the exception. */
    protected Throwable cause;

    /** Additional metadata or field-specific error details. */
    protected Map<String, String> errors;

    /**
     * Returns a self-reference to the builder cast to the specific subtype.
     * @return the builder instance (B).
     */
    @SuppressWarnings("unchecked")
    protected B self() { return (B) this; }

    /**
     * Populates the builder fields using a predefined {@link ErrorDefinition}.
     *
     * @param definition the error catalog definition to copy values from.
     * @return the builder instance (B).
     */
    public B from(ErrorDefinition definition) {
        this.errorCode = definition.getErrorCode();
        this.title = definition.getTitle();
        this.message = definition.getMessage();
        this.status = definition.getStatus();
        return self();
    }

    /**
     * Sets a custom error code.
     * @param errorCode the unique error identifier.
     * @return the builder instance (B).
     */
    public B errorCode(String errorCode) { this.errorCode = errorCode; return self(); }

    /**
     * Sets a custom title for the error.
     * @param title the error summary.
     * @return the builder instance (B).
     */
    public B title(String title) { this.title = title; return self(); }

    /**
     * Sets a custom error message.
     * @param message the detailed description.
     * @return the builder instance (B).
     */
    public B message(String message) { this.message = message; return self(); }

    /**
     * Sets the HTTP status code.
     * @param status the HTTP status integer.
     * @return the builder instance (B).
     */
    public B status(int status) { this.status = status; return self(); }

    /**
     * Sets the underlying cause of the exception.
     * @param cause the original {@link Throwable}.
     * @return the builder instance (B).
     */
    public B cause(Throwable cause) { this.cause = cause; return self(); }

    /**
     * Sets the metadata map for error details.
     * @param errors a map of details (e.g., validation messages).
     * @return the builder instance (B).
     */
    public B errors(Map<String, String> errors) { this.errors = errors; return self(); }

    /**
     * Abstract method to finalize the construction of the exception.
     *
     * @return the fully initialized exception instance (E).
     */
    public abstract E build();
}
