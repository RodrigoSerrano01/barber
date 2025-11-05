package br.com.v1.barber.exception.handler;

public class AppointmentAlreadyExistsException extends RuntimeException {

    public AppointmentAlreadyExistsException(String message){super(message);}
}
