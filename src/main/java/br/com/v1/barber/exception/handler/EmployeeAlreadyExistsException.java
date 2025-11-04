package br.com.v1.barber.exception.handler;

public class EmployeeAlreadyExistsException extends RuntimeException {

    public EmployeeAlreadyExistsException(String message){super(message);}
}
