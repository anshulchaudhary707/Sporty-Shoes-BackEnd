package com.demo.sporty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.sporty.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IntegrityConstraintViolation.class)
	public ResponseEntity<ApiResponse> integrityConstraintViolation(IntegrityConstraintViolation ex){
		String message = ex.getMessage();
		ApiResponse ar = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(ar,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<String> cartEmptyException(ApiException ex){
		String message = ex.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
	}
}