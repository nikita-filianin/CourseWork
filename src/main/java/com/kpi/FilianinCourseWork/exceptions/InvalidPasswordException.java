package com.kpi.FilianinCourseWork.exceptions;

public class InvalidPasswordException extends Exception {

    public InvalidPasswordException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public InvalidPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
