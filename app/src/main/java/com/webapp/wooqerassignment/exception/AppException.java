package com.webapp.wooqerassignment.exception;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 *
 * Custom excpetion handle defined buggy scenarios
 *
 */

public class AppException extends Exception{

    private int errorCode;

    private String errorMessage;

    public AppException(final int errorCode, final String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
