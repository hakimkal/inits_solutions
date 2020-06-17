package com.inits.expense.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inits.expense.model.Response;
import com.inits.expense.service.ExpenseService;
import com.inits.expense.service.UserService;
import com.inits.expense.serviceImpl.InitsException;

@RestController
public class UserController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/expense")
	public Response createExpense(@RequestParam(name = "value", required = true) String value, 
									@RequestParam(name = "reason", required = true) String reason,
									@RequestParam(name = "date",  required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
									@RequestParam(name = "username", required = true) String username) throws InitsException {
		//Needed the user email as I'm required not skip authentication
		Response response = new Response();
		if(expenseService.addExpense(value, reason, date, username)) {
			response.setMessage("Expense added successfully");
			response.setStatus(Response.SUCCESS);
			return response;
		}
		response.setMessage("Couldn't save expense");
		response.setStatus(Response.FAILED);
		return response;
	}
	
	@GetMapping("/expense")
	public Response getExpenses(@RequestParam(name = "username", required = true) String username) throws InitsException {
		Response response = new Response();
		
		response.setData(expenseService.getExpenses(username));
		response.setMessage("Expense fetched successfully");
		response.setStatus(Response.SUCCESS);
		
		return response;
	}
	
	@PostMapping("/register")
	public Response register(@RequestParam(name = "username", required = true) String username ) throws InitsException {
		Response response = new Response();
		userService.register(username);
		response.setMessage("Registration successful");
		response.setStatus(Response.SUCCESS);
		return response;

	}
	
}
