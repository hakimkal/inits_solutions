package com.inits.expense.service;

import java.time.LocalDate;
import java.util.Set;

import com.inits.expense.model.Expense;
import com.inits.expense.serviceImpl.InitsException;

public interface ExpenseService {

	boolean addExpense(String value, String reason, LocalDate date, String username) throws InitsException;
	
	Set<Expense> getExpenses(String username) throws InitsException;
}
