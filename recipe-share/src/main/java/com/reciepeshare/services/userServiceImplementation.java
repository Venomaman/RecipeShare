package com.reciepeshare.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reciepeshare.config.JwtProvider;
import com.reciepeshare.model.User;
import com.reciepeshare.repo.UserRepo;

@Service
public class userServiceImplementation implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> opt = userRepo.findById(userId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("user not find with this id " +userId);
		
	}

	@Override
	public User findUserByJwt(String jwt) throws Exception {
		// TODO Auto-generated method stub
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		
		if(email==null) {
			throw new Exception("provide valid token");
		}
		User user = userRepo.findUserByEmail(email);
		if(user== null) {
			throw new Exception("USer not find with email" +email);
		}
		return user;
	}

}
