package com.devportfolio.neuralchef.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s nicht gefunden mit %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
