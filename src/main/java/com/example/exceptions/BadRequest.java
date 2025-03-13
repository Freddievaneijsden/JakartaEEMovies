package com.example.exceptions;

public class BadRequest extends RuntimeException {
    public BadRequest() {super();}

    public BadRequest(String message) {
        super(message);
    }
}
