package com.inits.expense.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inits.expense.model.Expense;
import com.inits.expense.model.User;

@DataJpaTest
public class ExpenseRepositoryTest {
	
	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Test
	public void expenseUserRelationShipTest() {
		User user = new User();
		user.setUsername("test@init.com");
		Expense expense = new Expense();
		expense.setReason("reason");
		expense.setDate(LocalDate.parse("2010-11-11"));
		expense.setValue("Buy hoodie");
		expense.setUser(user);
		
		expenseRepo.save(expense);
		
		Optional<Expense> newExpense = expenseRepo.findById((long)1);
		
		Assertions.assertEquals("test@init.com", newExpense.get().getUser().getUsername());
	}
	
}
