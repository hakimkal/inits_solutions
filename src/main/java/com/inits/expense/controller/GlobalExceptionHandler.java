package com.inits.expense.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inits.expense.model.Response;
import com.inits.expense.serviceImpl.InitsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler
	public Response handleInitsException(InitsException exception) {
		Response response = new Response();
		
		response.setStatus(Response.FAILED);
		response.setMessage(exception.getMessage());
		
		
		return response;
	}

}