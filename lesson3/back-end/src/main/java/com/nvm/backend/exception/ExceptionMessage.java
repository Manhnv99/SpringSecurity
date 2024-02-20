package com.nvm.backend.exception;

public class ExceptionMessage extends RuntimeException{
    private String message;

    public ExceptionMessage(String message){
        this.message=message;
    }

    public String getMessage(){
        return this.message;
    }
}
