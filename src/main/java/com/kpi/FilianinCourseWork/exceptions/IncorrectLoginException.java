package com.kpi.FilianinCourseWork.exceptions;

public class IncorrectLoginException extends Exception {

    public IncorrectLoginException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    public IncorrectLoginException(String errorMsg) {
        super(errorMsg);
    }
}
