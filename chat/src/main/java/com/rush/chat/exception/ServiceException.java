package com.rush.chat.exception;

import java.util.Map;

public class ServiceException extends RuntimeException {

	private String exceptionCode;
	private String exceptionMesssage;
    private Map<String,Object> errorMap;
	
	

	public ServiceException(String exceptionCode, String exceptionMesssage) {
		this.exceptionCode = exceptionCode;
		this.exceptionMesssage = exceptionMesssage;
	}

    public ServiceException(String exceptionCode, String exceptionMesssage,Map<String,Object> errorMap) {
        this.exceptionCode = exceptionCode;
        this.exceptionMesssage = exceptionMesssage;
        this.errorMap=errorMap;
    }

	public ServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
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

    public Map<String, Object> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, Object> errorMap) {
        this.errorMap = errorMap;
    }
}
