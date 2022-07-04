package com.ticketTracker.ticketTracker.errorHandling;

import org.springframework.http.HttpStatus;

public class TicketTrackerException extends Exception{
    private final HttpStatus statusCode;
    public TicketTrackerException(String errorMessage, HttpStatus statusCode) {
        super(errorMessage);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
