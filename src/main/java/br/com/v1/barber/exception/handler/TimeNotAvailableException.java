package br.com.v1.barber.exception.handler;

public class TimeNotAvailableException extends RuntimeException{

    public TimeNotAvailableException(String message) {
        super(message);
    }
}
