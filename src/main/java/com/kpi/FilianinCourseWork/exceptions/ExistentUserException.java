package com.kpi.FilianinCourseWork.exceptions;

public class ExistentUserException extends Exception {

    public ExistentUserException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public ExistentUserException(String errorMessage) {
        super(errorMessage);
    }
}
