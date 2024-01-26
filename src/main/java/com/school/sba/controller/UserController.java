package com.school.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.serviceimpl.UserServiceImpl;
import com.school.sba.util.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	private UserService userservice;
	
	@PostMapping(value="/users/register")
 public ResponseEntity<ResponseStructure<UserResponse>> register(@RequestBody UserRequest userrequest)
	{
		return userservice.register(userrequest);
	}
	
  @PostMapping(value="/users/userId")
  public ResponseEntity<ResponseStructure<UserResponse>> getRegisterUser(@RequestBody int userId, UserRequest userrequest){
	   
	    return userservice.getRegisterUser(userId,userrequest);
  }

  
  
}
