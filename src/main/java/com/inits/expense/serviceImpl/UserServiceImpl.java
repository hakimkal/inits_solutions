package com.inits.expense.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inits.expense.model.User;
import com.inits.expense.repository.UserRepository;
import com.inits.expense.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepo;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public boolean register(String username) throws InitsException {
		if(userRepo.existsByUsername(username)) throw new InitsException("User already exists");
		
		User user = new User();
		
		user.setUsername(username);
		
		userRepo.save(user);
		
		return true;
		
	}

}
