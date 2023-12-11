package com.example.auth.exception;

import com.example.auth.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(NotFoundException ex,
                                                                        WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), request.getDescription(false), new Date()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictsException.class)
    public ResponseEntity<ErrorDetails> handleResourceConflictException(NotFoundException ex,
                                                                        WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), request.getDescription(false), new Date()),
                HttpStatus.CONFLICT);
    }

}
