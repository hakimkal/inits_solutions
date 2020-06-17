package com.inits.expense.repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inits.expense.model.Expense;
import com.inits.expense.model.User;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;
			
	@Test
	public void userExpenseRelationshipTest() {
		User user = new User();
		user.setUsername("test@inits.com");
		
		Set<Expense> exp = new HashSet<>();
		
		for(int i = 1; i<=3; i++) {
			Expense expense = new Expense();
			expense.setReason("reason"+ i);
			expense.setDate(LocalDate.parse("2010-11-11"));
			expense.setValue("Buy hoodie"+i);
			
			exp.add(expense);
		}
		user.setExpenses(exp);
		
		userRepo.save(user);
		
		Optional<User> newUser = userRepo.findByUsername("test@inits.com");
		
		Assertions.assertEquals(3, newUser.get().getExpenses().size());
		 
	}
}
