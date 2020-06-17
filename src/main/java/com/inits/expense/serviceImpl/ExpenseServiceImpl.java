package com.inits.expense.serviceImpl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inits.expense.model.Expense;
import com.inits.expense.model.User;
import com.inits.expense.repository.ExpenseRepository;
import com.inits.expense.repository.UserRepository;
import com.inits.expense.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	private ExpenseRepository expenseRepo;

	private UserRepository userRepo;
	
	@Autowired
	public ExpenseServiceImpl(ExpenseRepository expenseRepo, UserRepository userRepo) {
		this.expenseRepo = expenseRepo;
		this.userRepo = userRepo;
	}

	@Override
	public boolean addExpense(String value, String reason, LocalDate date, String username) throws InitsException {
		Optional<User> user = userRepo.findByUsername(username);
		if(!user.isPresent()) {
			throw new InitsException("User not found");
		}
		Expense expense = new Expense();
		
		expense.setValue(value);
		expense.setReason(reason);
		expense.setDate(date);
		expense.setUser(user.get());
		
		expenseRepo.save(expense);
		
		return true;		
	}

	@Override
	public Set<Expense> getExpenses(String username) throws InitsException {
		Optional<User> user = userRepo.findByUsername(username);
		if(!user.isPresent()) {
			throw new InitsException("User not found");
		}
		
		return user.get().getExpenses();
		
	}

}
