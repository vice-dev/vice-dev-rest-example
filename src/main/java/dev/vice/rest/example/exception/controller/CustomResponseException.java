package dev.vice.rest.example.exception.controller;

public class CustomResponseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CustomError error;
	
	public CustomResponseException(CustomError error) {
		super();
		this.error = error;
	}

	public CustomResponseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomResponseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CustomResponseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomResponseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CustomResponseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CustomError getError() {
		return error;
	}

	public void setError(CustomError error) {
		this.error = error;
	}
	

}
