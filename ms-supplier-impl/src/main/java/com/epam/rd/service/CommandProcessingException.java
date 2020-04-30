package com.epam.rd.service;

public class CommandProcessingException extends RuntimeException {
    public CommandProcessingException(String message) {
        super(message);
    }

    public CommandProcessingException() throws CommandProcessingException {
        throw new CommandProcessingException("CommandProcessingException");
    }
}