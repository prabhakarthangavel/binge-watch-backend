package com.mobileapp.bingewatch.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileapp.bingewatch.entity.Roles;
import com.mobileapp.bingewatch.entity.Users;
import com.mobileapp.bingewatch.exceptions.UserNameAlreadyExist;
import com.mobileapp.bingewatch.modals.Register;
import com.mobileapp.bingewatch.repo.UsersRepo;

@Service
public class AuthenticationService {

	@Autowired
	private UsersRepo usersRepo;
	
	public String getUsername(String username) {
		Users user = this.usersRepo.findusername(username);
		return user.getUsername();
	}
	
	public String getFullName(String username) {
		Users user = this.usersRepo.findusername(username);
		return user.getFirstname() + " " + user.getLastname();
	}
	
	public void createNewUser(Register request) throws UserNameAlreadyExist {
		Users user = new Users();
		user.setUsername(request.getUsername());
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setPassword(request.getPassword());
		Roles role = new Roles();
		role.setRole("USER");
		Set<Roles> set = new HashSet<>();
		set.add(role);
		user.setRoles(set);
		if(this.usersRepo.findusername(request.getUsername()) == null) {
//			this.usersRepo.save(user);
		}else {
			throw new UserNameAlreadyExist("User Name Already Exist!");
		}
	}
	
	public void checkUserName(String userName) throws UserNameAlreadyExist {
		if(this.usersRepo.findusername(userName) != null) throw new UserNameAlreadyExist("Email Already Exist!");
	}
}
