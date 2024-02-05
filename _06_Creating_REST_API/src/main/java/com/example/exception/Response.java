package com.example.exception;

import java.time.LocalDateTime;

public class Response {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public Response(LocalDateTime timestamp, String error, String details) {
        this.timestamp = timestamp;
        this.message = error;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
