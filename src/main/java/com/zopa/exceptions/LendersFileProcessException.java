package com.zopa.exceptions;

public class LendersFileProcessException extends RuntimeException {


    public LendersFileProcessException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
