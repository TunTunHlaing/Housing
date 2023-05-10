package com.example.housing.Exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.housing.Exception.Message.MessageType;

@RestControllerAdvice
public class ExceptionHandler {


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
    public Message handleExceptino(ValidationException e){
        return new Message(Message.MessageType.Error, e.getMessages());
    }
    
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(DataNotFoundException.class)
    public Message handleDataException(DataNotFoundException e) {
    	return new Message(MessageType.Error, List.of(e.getMessage()));
    }
}
