package dev.vice.rest.example.exception.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class CustomError {
	
	private HttpStatus httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
	private String httpStatusPhrase;
	private String customErrorCode;
	private String customErrorMessage;
	private Object errorDetails;
	private String path;
	private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

	public CustomError() {
		super();
	}
	
	public CustomError(HttpStatus httpStatusCode, String customErrorMessage) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.customErrorMessage = customErrorMessage;
	}

	public CustomError(HttpStatus httpStatusCode) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.httpStatusPhrase = httpStatusCode.getReasonPhrase();
	}

	public CustomError(HttpStatus httpStatusCode, Object errorDetails) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.errorDetails = errorDetails;
		this.httpStatusPhrase = httpStatusCode.getReasonPhrase();
	}
	public CustomError(HttpStatus httpStatusCode, String customErrorCode, String customErrorMessage,
			Object errorDetails) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.customErrorCode = customErrorCode;
		this.customErrorMessage = customErrorMessage;
		this.errorDetails = errorDetails;
		this.httpStatusPhrase = httpStatusCode.getReasonPhrase();
	}
	public CustomError(HttpStatus httpStatusCode, String customErrorCode, String customErrorMessage) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.customErrorCode = customErrorCode;
		this.customErrorMessage = customErrorMessage;
		this.httpStatusPhrase = httpStatusCode.getReasonPhrase();
	}
	public CustomError(String customErrorMessage) {
		super();
		this.customErrorMessage = customErrorMessage;
		this.httpStatusPhrase = httpStatusCode.getReasonPhrase();
	}
	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(HttpStatus httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public String getHttpStatusPhrase() {
		return httpStatusPhrase;
	}
	public void setHttpStatusPhrase(String httpStatusPhrase) {
		this.httpStatusPhrase = httpStatusPhrase;
	}
	public String getCustomErrorCode() {
		return customErrorCode;
	}
	public void setCustomErrorCode(String customErrorCode) {
		this.customErrorCode = customErrorCode;
	}
	public String getCustomErrorMessage() {
		return customErrorMessage;
	}
	public void setCustomErrorMessage(String customErrorMessage) {
		this.customErrorMessage = customErrorMessage;
	}
	public Object getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(Object errorDetails) {
		this.errorDetails = errorDetails;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
