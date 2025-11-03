package br.com.v1.barber.exception.handler;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException(String message){super(message);}
}
