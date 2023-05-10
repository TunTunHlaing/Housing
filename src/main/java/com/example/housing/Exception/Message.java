package com.example.housing.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Message {

    private MessageType type;
    private List<String> messages;
    public enum MessageType{
        Information, Alert, Error
    }

}
