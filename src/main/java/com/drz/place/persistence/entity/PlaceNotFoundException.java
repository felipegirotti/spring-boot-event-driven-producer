package com.drz.place.persistence.entity;

public class PlaceNotFoundException extends Exception {

    private Integer code = 404;

    private String message = "Place not found";

    public PlaceNotFoundException() {
    }

    public PlaceNotFoundException(String message, Integer code, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
