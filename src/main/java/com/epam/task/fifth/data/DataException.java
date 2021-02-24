package com.epam.task.fifth.data;

public class DataException extends Exception {

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataException(String message) {
        super(message);
    }
}
