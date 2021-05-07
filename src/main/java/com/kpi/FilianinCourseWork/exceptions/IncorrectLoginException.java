package com.kpi.FilianinCourseWork.exceptions;

public class IncorrectLoginException extends Exception {

    public IncorrectLoginException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public IncorrectLoginException(String errorMessage) {
        super(errorMessage);
    }
}
