package com.jetsen.exception;

import java.io.IOException;

/**
 * @author NICKEL
 */
public class StorageException extends RuntimeException {
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(String message) {
        super(message);
    }
}
