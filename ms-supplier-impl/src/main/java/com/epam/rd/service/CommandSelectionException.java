package com.epam.rd.service;

public class CommandSelectionException extends RuntimeException {
    public CommandSelectionException(String message) {
        super(message);
    }

    public CommandSelectionException() throws CommandSelectionException {
        throw new CommandSelectionException("CommandSelectionException");
    }
}
