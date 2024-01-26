package com.school.sba.serviceimpl;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.User;
import com.school.sba.enums.UserRole;
import com.school.sba.repository.UserRepository;
import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{
   
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ResponseStructure<UserResponse> responsestructure;
	
	private UserResponse mapToUserRespose(User user) {
		UserResponse response=new UserResponse();
		response.setUserId(user.getUserId());
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setContactNo(user.getContactNo());
		response.setUserName(user.getUserName());
		response.setEmail(user.getEmail());
		response.setUserrole(user.getUserrole());
		 return response;
	}
	
private User mapToUser(UserRequest userRequest) {
		
		User user=new User();
				user.setUserName(userRequest.getUserName());
				user.setEmail(userRequest.getEmail());
				user.setPassword(userRequest.getPassword());
				user.setFirstName(userRequest.getFirstName());
				user.setLastName(userRequest.getLastName());
				user.setContactNo(userRequest.getContactNo());
				user.setUserrole(userRequest.getUserrole());
				return user;	
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> getRegisterUser(int userId, UserRequest userrequest) {
			try {
				if(isPresent(UserRole.ADMIN)==false)
				{
					User getregisteruser=userrepository.save(mapToUser(userrequest));
					responsestructure.setStatus(HttpStatus.OK.value());
					  responsestructure.setMessage("User data registered successfully!!!");
					  responsestructure.setData(mapToUserRespose(getregisteruser));
					
				}
				else {
					throw new Exception("we can have only one admin");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return new ResponseEntity<ResponseStructure<UserResponse>> (responsestructure, HttpStatus.OK);
		
		}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> register(UserRequest userrequest) {

	     User user = userrepository.save(mapToUser(userrequest));
	    		  
	      responsestructure.setStatus(HttpStatus.OK.value());
		  responsestructure.setMessage("user registered successfully");
		  responsestructure.setData(mapToUserRespose(user));
	      
	      return new ResponseEntity<ResponseStructure<UserResponse>>(responsestructure, HttpStatus.CREATED);
		}
		
		public boolean isPresent(UserRole userrole)
		{
			boolean existsByUserRole=userrepository.existsByUserrole(UserRole.ADMIN);
			if(existsByUserRole==true)
			{
				throw new KeyAlreadyExistsException("We can have only one ADMIN");
			}
			return true;
		}
}
		
	
	

