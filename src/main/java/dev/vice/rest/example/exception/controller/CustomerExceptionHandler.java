package dev.vice.rest.example.exception.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@ExceptionHandler(CustomResponseException.class)
	public ResponseEntity<?> customExceptionHandler(CustomResponseException cre){
		cre.getError().setPath(httpServletRequest.getServletPath());
		return ResponseEntity.status(cre.getError().getHttpStatusCode()).body(cre.getError());
	}

}
