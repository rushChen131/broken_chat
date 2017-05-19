package com.rush.chat.exception;

import java.util.Map;

public class ParamsVaildErrException extends RuntimeException {

    private String exceptionCode;
    private String exceptionMesssage;
    private Map<String,String> errorMap;

    public ParamsVaildErrException(String exceptionCode, String exceptionMessage) {
        this.exceptionCode = exceptionCode;
        this.exceptionMesssage = exceptionMessage;
    }

    public ParamsVaildErrException(String exceptionCode, String exceptionMessage, Map<String, String> errorMap) {
        this.exceptionCode = exceptionCode;
        this.exceptionMesssage = exceptionMessage;
        this.errorMap=errorMap;
    }

    public ParamsVaildErrException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ParamsVaildErrException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsVaildErrException(String message) {
        super(message);
    }

    public ParamsVaildErrException(Throwable cause) {
        super(cause);
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMesssage() {
        return exceptionMesssage;
    }

    public void setExceptionMesssage(String exceptionMesssage) {
        this.exceptionMesssage = exceptionMesssage;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
}
