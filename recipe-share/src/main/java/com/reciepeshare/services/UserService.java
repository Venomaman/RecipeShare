package com.reciepeshare.services;

import com.reciepeshare.model.User;

public interface UserService {
	public User findUserById(Long userId)throws Exception;
	
	public User findUserByJwt(String jwt) throws Exception;

}
