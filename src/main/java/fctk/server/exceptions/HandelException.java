package fctk.server.exceptions;

public class HandelException extends Exception {
    public HandelException(String message) {
        super(message);
    }

    public HandelException(String message, Throwable cause) {
        super(message, cause);
    }
}
