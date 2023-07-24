package younah.TodoRefactor.global.exception;

import younah.TodoRefactor.domain.common.Status;

public class ApplicationException extends RuntimeException {
    private final Status status;

    protected ApplicationException(Status status) {
        this(status, status.message());
    }

    protected ApplicationException(Status status, String message) {
        super(message);
        this.status = status;
    }

    protected ApplicationException(Status status, Throwable cause) {
        this(status, status.message(), cause);
    }

    protected ApplicationException(Status status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public Status status() {
        return this.status;
    }

    public String message() {
        return super.getMessage();
    }
}
