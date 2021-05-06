package com.kpi.FilianinCourseWork.exceptions;

public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    public IncorrectPasswordException(String errorMsg) {
        super(errorMsg);
    }
}
