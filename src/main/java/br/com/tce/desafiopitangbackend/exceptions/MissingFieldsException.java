package br.com.tce.desafiopitangbackend.exceptions;

public class MissingFieldsException extends RuntimeException {
    public MissingFieldsException(String message) {
        super(message);
    }
}
