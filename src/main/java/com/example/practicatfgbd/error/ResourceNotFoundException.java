package com.example.practicatfgbd.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName) {
        super(String.format("No se encontraron registros de %s en el sistema.", resourceName));
        this.resourceName = resourceName;
    }
}
