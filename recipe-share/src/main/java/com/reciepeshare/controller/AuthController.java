package com.reciepeshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reciepeshare.config.JwtProvider;
import com.reciepeshare.dto.AuthResponse;
import com.reciepeshare.dto.LoginRequest;
import com.reciepeshare.model.User;
import com.reciepeshare.repo.UserRepo;
import com.reciepeshare.services.CustomeUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception{
		
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		
		User isExistEmail =userRepo.findUserByEmail(email);
		if(isExistEmail!=null) {
			throw new Exception("Email is already used with another account");
		}
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFullName(fullName);
		
		User savedUser = userRepo.save(createdUser);
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateTokeString(authentication);
		
		AuthResponse res = new AuthResponse();
		res.setJwt(token);
		res.setMessage("Signup Success!!");
		
		return res;
		
	}
	
	@PostMapping("/signin")
	public AuthResponse signinHandel(@RequestBody LoginRequest loginRequest) {
		String userName = loginRequest.getEmail();
		String passWord = loginRequest.getPassword();
		
		Authentication authentication = authenticate(userName, passWord);
				SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateTokeString(authentication);
		AuthResponse res = new AuthResponse();
		res.setJwt(token);
		res.setMessage("Signin Success!!");
		
		return res;
		
	}

	private Authentication authenticate(String userName, String passWord) {
		// TODO Auto-generated method stub
		UserDetails userDetails = customeUserDetailsService.loadUserByUsername(userName);
		
		if(userDetails==null) {
			throw new BadCredentialsException("User not found");
		}
		if(!passwordEncoder.matches(passWord, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}

}
