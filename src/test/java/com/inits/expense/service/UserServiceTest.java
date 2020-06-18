package com.inits.expense.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inits.expense.model.User;
import com.inits.expense.repository.UserRepository;
import com.inits.expense.serviceImpl.InitsException;
import com.inits.expense.serviceImpl.UserServiceImpl;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserService userService;
	
	@Mock
	private UserRepository userRepo;
	
	@BeforeEach
	public void setup() {
		Mockito.when(userRepo.existsByUsername("user@inits.com")).thenReturn(true);
		Mockito.when(userRepo.existsByUsername("support@inits.com")).thenReturn(false);
		userService = new UserServiceImpl(userRepo);
	}
	
	@Test
	public void registerTest() throws InitsException {
		userService.register("support@inits.com");
		Mockito.verify(userRepo).save(Mockito.any(User.class));
		
		assertEquals(true, userService.register("support@inits.com"));
		
		assertThrows(InitsException.class, () -> userService.register("user@inits.com"));
		
	}

}
