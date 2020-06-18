package com.inits.expense.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inits.expense.model.Expense;
import com.inits.expense.model.User;
import com.inits.expense.repository.ExpenseRepository;
import com.inits.expense.repository.UserRepository;
import com.inits.expense.serviceImpl.ExpenseServiceImpl;
import com.inits.expense.serviceImpl.InitsException;

@ExtendWith(SpringExtension.class)
public class ExpenseServiceTest {
	
	@Mock
	private ExpenseService expenseService;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private ExpenseRepository expenseRepo;
	
	User testUser = new User();
	
	@BeforeEach
	public void setup() {
		testUser.setUsername("user@inits.com");
		testUser.setId((long)1);
		Mockito.when(userRepo.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));
		Mockito.when(userRepo.findByUsername("support@inits.com")).thenReturn(Optional.empty());
		expenseService = new ExpenseServiceImpl(expenseRepo, userRepo);
		
	}
	
	@Test
	public void addExpenseTest() throws InitsException {
		expenseService.addExpense("Buy headSet", "Needed for coding", LocalDate.parse("2008-11-11"), "user@inits.com");
		
		Mockito.verify(expenseRepo).save(Mockito.any(Expense.class));
		
		assertThrows(InitsException.class, () -> expenseService.addExpense("Buy headSet", "Needed for coding", LocalDate.parse("2008-11-11"), "support@inits.com"));
	}
	

}
