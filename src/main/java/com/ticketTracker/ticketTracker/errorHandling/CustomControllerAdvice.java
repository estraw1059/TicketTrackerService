package com.ticketTracker.ticketTracker.errorHandling;

import com.ticketTracker.ticketTracker.model.TicketTracker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException( Exception e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketTrackerException.class)
    public ResponseEntity<ErrorResponse> handleTicketTrackerException(TicketTrackerException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getStatusCode(),
                        e.getMessage()),
                e.getStatusCode());
    }
}
