package com.epam.rd.repository;

public class PersistenceException extends RuntimeException {
    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException() throws PersistenceException {
        throw new PersistenceException("CommandProcessingException");
    }
}