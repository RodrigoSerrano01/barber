package br.com.v1.barber.exception.handler;

public class ServicesNotFoundException extends RuntimeException {

    public ServicesNotFoundException(String message) {
        super(message);
    }
}
