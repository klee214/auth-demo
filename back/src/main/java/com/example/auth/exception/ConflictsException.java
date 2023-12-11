package com.example.auth.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictsException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ConflictsException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s conflicts %s : %s", resourceName, fieldName, fieldValue));

        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
