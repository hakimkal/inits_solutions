package com.inits.expense.service;

import com.inits.expense.serviceImpl.InitsException;

public interface UserService {
	boolean register(String username) throws InitsException;
}
