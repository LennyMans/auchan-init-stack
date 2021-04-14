package init.stack.api.model;

import java.util.Date;

public class ErrorDetails {

    // -- CONS
    private Date timestamp;
    private String message;
    private String details;

    // -- CONSTRUCTOR
    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // -- GETTERS
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}