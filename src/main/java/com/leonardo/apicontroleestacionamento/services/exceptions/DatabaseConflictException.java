package com.leonardo.apicontroleestacionamento.services.exceptions;

public class DatabaseConflictException extends RuntimeException{

    public DatabaseConflictException(String message){
        super(message);
    }

}