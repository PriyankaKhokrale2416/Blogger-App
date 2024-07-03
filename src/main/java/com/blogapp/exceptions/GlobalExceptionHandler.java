package com.blogapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException resNotFoundExc)
	{
		String message = resNotFoundExc.getMessage();
		ApiResponse apiResp = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResp, HttpStatus.NOT_FOUND);
		
	}

}
