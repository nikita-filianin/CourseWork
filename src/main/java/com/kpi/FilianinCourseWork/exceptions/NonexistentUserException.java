package com.kpi.FilianinCourseWork.exceptions;

public class NonexistentUserException extends Exception {

    public NonexistentUserException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public NonexistentUserException(String errorMessage) {
        super(errorMessage);
    }
}
