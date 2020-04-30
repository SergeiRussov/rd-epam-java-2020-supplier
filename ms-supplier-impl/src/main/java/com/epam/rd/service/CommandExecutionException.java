package com.epam.rd.service;

public class CommandExecutionException extends RuntimeException {
    public CommandExecutionException(String message) {
        super(message);
    }

    public CommandExecutionException() throws CommandExecutionException {
        throw new CommandExecutionException("CommandExecutionException");
    }
}
